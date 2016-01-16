package DAO;

import database.Db;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.sql.*;

/**
 * Created by Jy on 13-Dec-15.
 */
public class Key {
    public static void store(String username, String key) {
        Connection conn = Db.getConnection();
        String sql = "INSERT INTO diario.`keys` (username, pubkey) VALUES (?, ?);";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, key);
            int status = preparedStmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static PublicKey getPubKey(String username) {
        Connection conn = Db.getConnection();
        String query = "SELECT pubkey FROM diario.`keys` where username = '" + username + "';";

        Statement st = null;
        String pubKey = null;
        PublicKey pk = null;

        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                pubKey = rs.getString("pubkey");
            }
        }
        catch   (SQLException e)    {
            e.printStackTrace();
        }

        if (pubKey != null) {
            byte[] keyBytes = new byte[0];
            try {
                keyBytes = Base64.decodeBase64(pubKey.getBytes("utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = null;
            try {
                keyFactory = KeyFactory.getInstance("RSA");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            try {
                pk = keyFactory.generatePublic(spec);
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        return pk;
    }
}
