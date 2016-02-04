package authentication;

import database.Db;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IamFeeK on 25/1/2016.
 */
@WebServlet(name = "ResetPasswordNow")
public class ResetPasswordNow extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Stage 2 reset");
        String username = request.getParameter("username");
        String key = request.getParameter("key");
        String password = request.getParameter("password");
        String salt = request.getParameter("salt");
        String verifier = request.getParameter("verifier");
        try {
            boolean result = doReset(username, key, password, salt, verifier);
            if(result) {
                response.getWriter().write("done");
                System.out.println("Updated");
                deleteKey(username);
            }else {
                response.getWriter().write("bad");
                System.out.println("Failed to update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean doReset(String username, String key, String password, String salt, String verifier) throws SQLException {
        boolean keyIsTrue = getKey(username, key);
        System.out.println(keyIsTrue);
        if(keyIsTrue){
            Connection conn = Db.getConnection();
            String sql = "UPDATE accounts set salt = ?, verifier = ? where username = ?";
            System.out.println(sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, salt);
            pstmt.setString(2, verifier);
            pstmt.setString(3, username);

            pstmt.executeUpdate();
            return true;
        } else
            return false;
    }

    private static boolean getKey(String username, String key) throws SQLException {
        Connection conn = Db.getConnection();
        String sql = "select rand from reset where username = '"+username+"';";

        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement(sql);
        System.out.println(pstmt);
        ResultSet rs = pstmt.executeQuery();

        String rand = null;
        while(rs.next()){
            rand = rs.getString("rand");
        }

        System.out.println("KEY: " + key);
        System.out.println("RAND: " + rand);
        return key.equals(rand);

    }

    private void deleteKey(String username){
        try{
            Connection conn = Db.getConnection();
            String query = "delete FROM reset where username = ?;";

            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            System.out.println("Deleted Rand.");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
