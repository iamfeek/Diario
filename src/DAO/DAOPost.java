package DAO;

import database.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Jy on 14-Dec-15.
 */
public class DAOPost {
    public static void storeMessage(String username, String text, boolean encrypted)    {
        Connection conn = Db.getConnection();
        String sql = "INSERT INTO diario.`posts` (username, text, encrypted) VALUES (?, ?, ?);";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, text);
            preparedStmt.setBoolean(3, encrypted);
            int status = preparedStmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
