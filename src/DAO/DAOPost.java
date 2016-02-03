package DAO;

import com.mysql.jdbc.Statement;
import database.Db;
import post.Post;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Jy on 14-Dec-15.
 */
public class DAOPost {
    public static void incrementPost(String username){
        Connection conn = Db.getConnection();
        String sql = "UPDATE PROFILES SET POSTS=POSTS + 1 WHERE username=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static int storeMessage(String username, String text, boolean encrypted)    {
        Connection conn = Db.getConnection();
        String sql = "INSERT INTO diario.`posts` (username, text, encrypted) VALUES (?, ?, ?);";
        PreparedStatement preparedStmt = null;
        int postid = -1;
        try {
            preparedStmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, text);
            preparedStmt.setBoolean(3, encrypted);
            int status = preparedStmt.executeUpdate();
            ResultSet rs = preparedStmt.getGeneratedKeys();
            while (rs.next())   {
                postid = rs.getInt(1);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Posted! Post id returned: " + postid);
        incrementPost(username);
        return postid;
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
        incrementPost(username);
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
                Timestamp timestamp = rs.getTimestamp("timestamp");
                posts.add(new Post(rs.getInt("postid"), rs.getString("username"), rs.getString("text"), rs.getBoolean("encrypted"), rs.getBoolean("shared"), new java.util.Date(timestamp.getTime())));
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
                Timestamp timestamp = rs.getTimestamp("timestamp");
                posts.add(new Post(rs.getInt("postid"), rs.getString("username"), rs.getString("text"), rs.getBoolean("encrypted"), rs.getBoolean("shared"), new java.util.Date(timestamp.getTime())));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static java.util.Date getPostDate(int postid)    {
        Connection conn = Db.getConnection();
        String query = "SELECT timestamp FROM diario.posts WHERE postid='" + postid + "'";

        PreparedStatement preparedStmt = null;
        Date date = null;
        try {
            preparedStmt = conn.prepareStatement(query);
            ResultSet rs = preparedStmt.executeQuery();
            rs.next();
            Timestamp timestamp = rs.getTimestamp("timestamp");
            date = new java.util.Date(timestamp.getTime());
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return date;
    }
}
