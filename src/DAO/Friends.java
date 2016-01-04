package DAO;

import database.Db;
import java.sql.*;
import java.util.ArrayList;

public class Friends {
    //Add friends together, username and friends_username. Remember to check for existing friend (a,b) and (b,a)
    public static boolean addFriend(String username, String friends_username) {
        Connection conn = Db.getConnection();
        String sql = "INSERT INTO diario.`friends` (username, f_username) VALUES (?, ?);";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, friends_username);
            int status = preparedStmt.executeUpdate();
            conn.close();
            if (status == 1)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Get username Arraylist<String> of all friends of <username>
    public static ArrayList<String> getFriendsList(String username) {
        ArrayList<String> friends_list = new ArrayList<String>();

        Connection conn = Db.getConnection();
        String query = "SELECT * FROM friends where username = '" + username + "' or f_username = '" + username + "';";

        Statement st = null;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                if (!rs.getString("f_username").equals(username))
                    friends_list.add(rs.getString("f_username"));
                else
                    friends_list.add(rs.getString("usernames"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends_list;
    }

    //Remove friend relationship beween <username> and <friends_username>. Will auto check for both directions
    public static boolean removeFriend(String username, String friends_username)   {
        boolean tryOther = false;

        Connection conn = Db.getConnection();
        String sql = "DELETE FROM `diario`.`friends` WHERE `username`=? and`f_username`=?;";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, friends_username);
            int status = preparedStmt.executeUpdate();
            conn.close();
            if (status == 1)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            tryOther = true;
        }

        if (tryOther)   {
            conn = Db.getConnection();
            sql = "DELETE FROM `diario`.`friends` WHERE `username`=? and`f_username`=?;";
            preparedStmt = null;
            try {
                preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, friends_username);
                preparedStmt.setString(2, username);
                int status = preparedStmt.executeUpdate();
                conn.close();
                if (status == 1)
                    return true;
                else
                    return false;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
