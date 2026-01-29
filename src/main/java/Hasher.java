import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class Hasher {
    /**
     * Hashes a password with a randomly generated salt.
     * @param password The password to hash.
     * @return The hashed password.
     * @throws Exception If an error occurs in the process.
     */
    public static String hashPassword(String password) throws Exception {
        byte[] salt = getSalt();
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
    }

    /**
     * Verifies the password against the stored hash.
     * @param password The password to verify.
     * @param storedHash The hashed password to compare with.
     * @return True if the passwords match, false if they don't.
     * @throws Exception If an error occurs in the process.
     */
    public static boolean verifyPassword(String password, String storedHash) throws Exception {
        String[] parts = storedHash.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] hash = Base64.getDecoder().decode(parts[1]);

        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = factory.generateSecret(spec).getEncoded();

        // Compare the hashes
        return Base64.getEncoder().encodeToString(testHash).equals(Base64.getEncoder().encodeToString(hash));
    }

    /**
     * Generates a random salt.
     * @return The generated salt.
     * @throws Exception If an error occurs in the process.
     */
    private static byte[] getSalt() throws Exception {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16]; // 16 bytes
        sr.nextBytes(salt);
        return salt;
    }
}
