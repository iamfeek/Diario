package friends;

import DAO.Friend;
import com.sun.deploy.net.HttpUtils;
import org.eclipse.jetty.http.HttpURI;
import org.eclipse.jetty.server.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lenovo on 1/3/2016.
 */
public class FriendsBean {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:8888/diario";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "narumi";



    public void unfriend (int id){
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            stmt = con.createStatement();
            stmt.execute("DELETE FROM friends WHERE fUID=" + String.valueOf(id));
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null){
                    stmt.close();
                }
                if (con != null){
                    con.close();
                }
            }catch (SQLException s){
                s.printStackTrace();
            }
        }

    }

    public void view (){
        Connection con = null;
        Statement stmt = null;

    }
    public void message (int id){
        Connection con = null;
        Statement stmt = null;

    }

    private List Search (Connection con, String search) throws SQLException {
        List friends = new LinkedList();
        String q = "SELECT fUNAME FROM friends where fUNAME LIKE ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(q);
            ps.setString(1, "%" + search + "%");

            ResultSet rs = ps.executeQuery();
            while ( rs.next() )
            {
                Friend f = new Friend();
                f.setfUNAME(rs.getString(2));
                friends.add(f);
            }
            rs.close();
            ps.close();
        } catch (SQLException se){
            se.printStackTrace();
        }
        return friends;

    }

    public Friend getFriend (int id) {
        Friend f = null;
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM profiles WHERE id=" + id);
            if (rs.next()) {
                f = new Friend();
                f.setfUNAME(rs.getString(2));
                f.setPosts(rs.getString(3));
                f.setFriends(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
                try {
                    if (stmt != null) stmt.close();
                    if (con != null) con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return f;
    }

    public List<Friend> getFriends(){
        List<Friend> list = new ArrayList<Friend>();
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM friends ORDER BY fUNAME");
            while (rs.next()) {
                Friend f = new Friend();
                f.setId(rs.getInt(1));
                f.setfUNAME(rs.getString(2));
                f.setLocation(rs.getString(3));
                f.setMF(rs.getString(4));

                list.add(f);
            }

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } return list;
    }


}
