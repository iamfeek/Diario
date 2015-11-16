package authentication;

import database.Db;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by: Syafiq Hanafee
 * Dated: 16/11/15.
 */
public class LoginHandler {
    public static void login(String username, String password, javax.servlet.http.HttpServletResponse response) throws InvalidKeySpecException, NoSuchAlgorithmException, SQLException {
        System.out.println("[Login Handler] Authenticating...");

        //retrieving password from DB;
        Connection conn = Db.getConnection();
        String query = "SELECT password FROM accounts where username = '" + username + "';";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        String retrievedPassword = null;
        while(rs.next()){
            retrievedPassword = rs.getString("password");
        }

        if(PasswordHash.validatePassword(password, retrievedPassword)){
            //legit account
            System.out.println("LEGIT ACCOUNT");
        } else {
            System.out.println("FAILED");
        }
    }

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException, SQLException {
        login("syafiqhanafee", "123123", null);
    }
}
