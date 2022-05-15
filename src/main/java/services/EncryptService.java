package services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class EncryptService {
    private final PasswordEncryptor encryptor = new PasswordEncryptor();

    public String encryptPassword(String password){
        String resultHash = "";
        try {
            resultHash = encryptor.createHash(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return resultHash;
    }

    public boolean validatePassword(String password, String hash){
        try {
            return PasswordEncryptor.validatePassword(password, hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return false;
    }

}