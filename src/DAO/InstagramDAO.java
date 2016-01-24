package DAO;

import database.Db;
import org.jinstagram.auth.model.Token;

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
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, token.getToken());
            preparedStmt.setString(2, token.getSecret());
            preparedStmt.setString(3, username);

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
        String query = "SELECT * FROM diario.`InstagramAccessToken` where username = '" + username + "'";

        PreparedStatement preparedStmt = null;

        try {
            preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();

            rs.next();
            Token token = new Token(rs.getString("token"), rs.getString("secret"));

            System.out.println(token);
            conn.close();

            return token;

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

}
