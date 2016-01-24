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
        int key = Integer.parseInt(request.getParameter("key"));
        String password = request.getParameter("password");
        String salt = request.getParameter("salt");
        String verifier = request.getParameter("verifier");
        try {
            boolean result = doReset(username, key, password, salt, verifier);
            if(result)
                System.out.println("Updated!");
            else
                System.out.println("Failed to update");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean doReset(String username, int key, String password, String salt, String verifier) throws SQLException {
        boolean keyIsTrue = getKey(username, key);
        if(keyIsTrue){
            String sql = "UPDATE accounts set salt = ?, verifier = ? where username = ?";
            PreparedStatement pstmt = Db.getConnection().prepareStatement(sql);
            pstmt.setString(1, salt);
            pstmt.setString(2, verifier);
            pstmt.setString(3, username);

            pstmt.executeUpdate();
            return true;
        } else
            return false;
    }

    private static boolean getKey(String username, int key) throws SQLException {
        Connection conn = Db.getConnection();
        String sql = "select rand from reset where username = ?";
        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        int rand = 0;
        while(rs.next()){
            rand = rs.getInt("rand");
        }

        if(key == rand)
            return true;
        else return false;

    }
}
