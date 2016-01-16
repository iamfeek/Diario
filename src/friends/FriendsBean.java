package friends;

import DAO.Friend;
import database.Db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 1/3/2016.
 */
public class FriendsBean{
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:8888/diario";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "narumi";

    //Add friends together, username and friends_username. Remember to check for existing friend (a,b) and (b,a)
    public void addFriend(Friend f) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            pstmt = con.prepareStatement("INSERT INTO friends (id, fUNAME, Location, MF) VALUES (?, ?, ?, ?);");
            pstmt.setInt(1, f.getId());
            pstmt.setString(2, f.getfUNAME());
            pstmt.setString(3, f.getLocation());
            pstmt.setString(4, f.getMF());
            pstmt.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();

        } finally {
            try {
                if (pstmt != null){
                    pstmt.close();
                }
                if (con != null){
                    con.close();
                }
            }catch (SQLException s){
                s.printStackTrace();
            }
        }
    }

    //Get username Arraylist<String> of all friends of <username>
    public static ArrayList<String> getFriendsList(Friend f) {
        ArrayList<String> friends_list = new ArrayList<String>();
        String username = f.getUsername();

        Connection conn = Db.getConnection();
        String query = "SELECT * FROM friends where username = '" + username + "' or fUNAME = '" + username + "';";

        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                if (!rs.getString("fUNAME").equals(username))
                    friends_list.add(rs.getString("fUNAME"));
                else
                    friends_list.add(rs.getString("username"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends_list;
    }


    public void unfriend (int id){
        Connection conn = Db.getConnection();
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM friends WHERE id=" + String.valueOf(id));
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null){
                    stmt.close();
                }
                if (conn != null){
                    conn.close();
                }
            }catch (SQLException s){
                s.printStackTrace();
            }
        }

    }

    public void view (){
        Connection conn = Db.getConnection();
        Statement stmt = null;

    }
    public void message (int id){
        Connection conn = Db.getConnection();
        Statement stmt = null;

    }


    public Friend getFriend (int id) {
        Friend f = null;
        Connection conn = Db.getConnection();
        Statement stmt = null;
        try {
              stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM profiles WHERE id=" + id);
            if (rs.next()) {
                f = new Friend();
                f.setId(rs.getInt(1));
                f.setUsername(rs.getString(2));
                f.setLocation(rs.getString(3));
                f.setMF(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
                try {
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return f;
    }

    public List<Friend> getFriends(){
        List<Friend> list = new ArrayList<Friend>();
        Connection conn = Db.getConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM friends ORDER BY fUNAME");
            while (rs.next()) {
                Friend f = new Friend();
                f.setId(rs.getInt(1));
                f.setfUNAME(rs.getString(3));
                f.setLocation(rs.getString(4));
                f.setMF(rs.getString(5));

                list.add(f);
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } return list;
    }


}
