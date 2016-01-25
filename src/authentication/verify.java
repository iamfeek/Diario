package authentication;

import database.Db;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IamFeeK on 25/1/16.
 */
@WebServlet(name = "Verify")
public class Verify extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String token = request.getParameter("token");

        System.out.println("Verifying account: " + email);
        System.out.println("Token for account:" + token);

        PrintWriter pw = response.getWriter();
        pw.write("Verifying account....");

        String retrievedToken = getToken(email);
        System.out.println("Token from DB: " + retrievedToken);
        if(retrievedToken != null){
            if(token.equals(retrievedToken)){
                System.out.println("Token is valid. Verified.");
                hasVerified(email);
                pw.write("Success! Please <a href='/signin'>log in</a>");
                try{
                    Thread.sleep(5000);
                    response.sendRedirect("/signin");
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            } else {
                System.out.println("Token invalid");
            }
        }

    }

    private static void hasVerified(String email){
        Connection conn = Db.getConnection();
        String sql = "UPDATE accounts set verified = 1 where email_address = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);

            pstmt.executeUpdate();
            System.out.println("Updated " + email + " with verified: 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        deleteToken(email);
    }

    private static void deleteToken(String email){
        Connection conn = Db.getConnection();
        String sql = "delete FROM verify where email_address = ?;";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            System.out.println(pstmt.toString());
            pstmt.executeUpdate();
            System.out.println("Deleted " + email + " from verify table");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private String getToken(String email){
        Connection conn = Db.getConnection();
        String sql = "SELECT token from verify where email_address = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            System.out.println("Statement: " + pstmt.toString());

            ResultSet rs = pstmt.executeQuery();
            String token = null;
            while(rs.next()){
                token = rs.getString("token");
            }
            return token;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
