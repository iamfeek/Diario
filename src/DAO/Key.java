package DAO;

import database.Db;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemObjectParser;
import org.bouncycastle.util.io.pem.PemReader;

import javax.xml.bind.DatatypeConverter;
import java.io.StringReader;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
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

        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                pubKey = rs.getString("pubkey");
            }
        }
        catch   (SQLException e) {
            e.printStackTrace();
        }

        pubKey = pubKey.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
        byte[] keyBytes = Base64.decodeBase64(pubKey);

        // generate public key
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        PublicKey publicKey = null;
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(spec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return publicKey;
    }
}
