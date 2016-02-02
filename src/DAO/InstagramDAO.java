package DAO;

import database.Db;
import instagram.instagramKey;
import org.apache.commons.codec.binary.Base64;
import org.jinstagram.auth.model.Token;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by glenice on 24 Jan 2016.
 */

public class InstagramDAO {

    public static boolean saveToken(Token token, String username) {

        Connection conn = Db.getConnection();
        String sql = "INSERT INTO diario.`InstagramAccessToken` (token, secret, username) VALUES (?, ?, ?);";

        byte[] key = generateKey();
        byte[] iv = generateIV();
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, encrypt(key, iv, token.getToken()));
            preparedStmt.setString(2, token.getSecret());
            preparedStmt.setString(3, username);

            preparedStmt.execute();
            saveKeys(key, iv, username);
            conn.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean saveKeys(byte[] key, byte[] iv, String username) {
        Connection conn = Db.getConnection();
        String sql = "INSERT INTO diario.`instagramkey` (`key`,iv, username) VALUES (?,?,?);";

        PreparedStatement preparedStmt = null;
        try {

            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setBytes(1, key);
            preparedStmt.setBytes(2, iv);
            preparedStmt.setString(3, username);
            System.out.println(preparedStmt.toString());
            preparedStmt.execute();
            conn.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Token getToken(String username) {
        Connection conn = Db.getConnection();
        String query = "SELECT * FROM diario.`InstagramAccessToken` where username = '" + username + "';";

        PreparedStatement preparedStmt = null;

        try {
            preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();

            if (rs.next()) {
                Token token = new Token(rs.getString("token"), rs.getString("secret"));
                System.out.println("Token available.");

                instagramKey key = getKeys(username);

                token = new Token(decrypt(key.getKey(), key.getIv(), token.getToken()), decrypt(key.getKey(), key.getIv(), token.getSecret()));
                conn.close();

                return token;
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static instagramKey getKeys(String username) {
        Connection conn = Db.getConnection();
        String query = "SELECT * FROM diario.`instagramkey` where username = '" + username + "';";

        PreparedStatement preparedStmt = null;

        try {
            preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();

            if (rs.next()) {

                instagramKey key = new instagramKey(rs.getBytes("key"), rs.getBytes("iv"));
                conn.close();

                return key;
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /*public static boolean updateToken(Token token, String username){

        Connection conn = Db.getConnection();
        String sql = "UPDATE diario.`InstagramAccessToken` (token, secret, username) VALUES (?, ?, ?);";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, token.getToken());
            preparedStmt.setString(2, token.getSecret());
            preparedStmt.setString(3, username);

            conn.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }*/


    public static String encrypt(byte[] key, byte[] initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector);
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println("encrypted string: " + Base64.encodeBase64String(encrypted));

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(byte[] key, byte[] initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector);
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static byte[] generateKey() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[16]; // 128 bits are converted to 16 bytes;
        random.nextBytes(bytes);

        return bytes;
    }

    public static byte[] generateIV() {
        try {
            SecureRandom randomSecureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] iv = new byte[16];
            randomSecureRandom.nextBytes(iv);

            IvParameterSpec ivParams = new IvParameterSpec(iv);
            return iv;
        } catch (NoSuchAlgorithmException ex) {

        }
        return null;

    }
}
