package post;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import DAO.Key;
import org.apache.commons.codec.binary.Base64;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

/**
 * Created by Jy on 17-Jan-16.
 */
public class Encryption {
    public static String encryptWithAES(String plaintext, String base64key) {
        // decode the base64 encoded string
        byte[] decodedKey = Base64.decodeBase64(base64key);
        // rebuild key using SecretKeySpec
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, originalKey);
            byte[] encVal = cipher.doFinal(plaintext.getBytes());
            String encryptedValue = Base64.encodeBase64String(encVal);
            return encryptedValue;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateBase64AESKey()    {
        try {
            SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
            byte[] bytes = secretKey.getEncoded();
            String encodedKey = Base64.encodeBase64String(secretKey.getEncoded());
            return encodedKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String generateAESxRSAKeyForUser(String username, String AESKey)  {
        PublicKey pk = Key.getPubKey(username);
        try {
            byte[] cipherText;
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pk);
            cipherText = cipher.doFinal(AESKey.getBytes());
            return Base64.encodeBase64String(cipherText);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
