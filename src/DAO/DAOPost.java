package DAO;

import com.mysql.jdbc.Statement;
import database.Db;
import post.Post;

import java.sql.*;
import java.util.ArrayList;

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

    public static int storeMessage(String username, String text, boolean encrypted, boolean shared)    {
        Connection conn = Db.getConnection();
        String sql = "INSERT INTO diario.`posts` (username, text, encrypted, shared) VALUES (?, ?, ?, ?);";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, text);
            preparedStmt.setBoolean(3, encrypted);
            preparedStmt.setBoolean(4, shared);
            int status = preparedStmt.executeUpdate();
            ResultSet rs = preparedStmt.getGeneratedKeys();
            int postid = -1;
            if (rs != null && rs.next()) {
                postid = rs.getInt(1);
            }
            conn.close();
            return postid;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void storeAESKey(int postid, String username, String aeskey)    {
        Connection conn = Db.getConnection();
        String sql = "INSERT INTO diario.`posts_keys` (postid, username, aeskey) VALUES (?, ?, ?);";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setInt(1, postid);
            preparedStmt.setString(2, username);
            preparedStmt.setString(3, aeskey);
            int status = preparedStmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getAESKey(int postid, String username)   {
        Connection conn = Db.getConnection();
        String query = "SELECT * FROM diario.`posts_keys` where postid = '" + postid + "' and username = '" + username + "';";

        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            rs.next();
            String aeskey = rs.getString("aeskey");
            conn.close();
            return aeskey;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Post> getPosts(String username)   {
        Connection conn = Db.getConnection();
        String query = "SELECT * FROM diario.posts WHERE username='" + username + "'";

        PreparedStatement preparedStmt = null;
        ArrayList<Post> posts = new ArrayList<Post>();
        try {
            preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next())   {
                posts.add(new Post(rs.getInt("postid"), rs.getString("username"), rs.getString("text"), rs.getBoolean("encrypted"), rs.getBoolean("shared")));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static ArrayList<Post> getSharedPosts(String username)   {
        Connection conn = Db.getConnection();
        String query = "SELECT * FROM diario.posts WHERE username='" + username + "' AND shared='1'";

        PreparedStatement preparedStmt = null;
        ArrayList<Post> posts = new ArrayList<Post>();
        try {
            preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next())   {
                posts.add(new Post(rs.getInt("postid"), rs.getString("username"), rs.getString("text"), rs.getBoolean("encrypted"), rs.getBoolean("shared")));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
}
