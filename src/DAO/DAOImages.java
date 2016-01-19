package DAO;

import database.Db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Jy on 17-Jan-16.
 */
public class DAOImages {
    public static boolean postImage(String username, int secu, InputStream imageStream) {
        Connection conn = Db.getConnection();
        String sql = "INSERT INTO diario.`images` (username, img, secu) VALUES (?, ?, ?);";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, username);
            preparedStmt.setBlob(2, imageStream);
            preparedStmt.setInt(3, secu);
            int status = preparedStmt.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
