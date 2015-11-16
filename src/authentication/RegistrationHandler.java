package authentication;

import database.Db;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

/**
 * Created by: Syafiq Hanafee
 * Dated: 16/11/15.
 */
public class RegistrationHandler {
    public static int register(String username, String password, String email) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
        Connection conn = Db.getConnection();
        int status = 0;

        status = usernameAndEmailAvailable(username, email);
        if(status != 1){
            return status;//return status coz status is already 0
        }

        if (!username.equals("") && !password.equals("") && !email.equals("")) {
            String hashedpassword = PasswordHash.createHash(password);

            //generating the sqls and stuff before executeUpdate
            String sql = "INSERT INTO accounts (username, password, email_address) VALUES (?, ?, ?);";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, hashedpassword);
            preparedStmt.setString(3, email);

            //more than 1 means success.
            status = preparedStmt.executeUpdate();
            conn.close();
        }

        return status;
    }

    public static int usernameAndEmailAvailable(String username, String email) throws SQLException {
        String usernameSQL = "SELECT username FROM accounts WHERE username = '" + username + "';";
        String emailSQL = "SELECT username FROM accounts WHERE email_address='" + email + "';";
        System.out.println("[Registration Handler] Username Query: " + usernameSQL);
        System.out.println("[Registration Handler] Email Query:    " + emailSQL);
        Connection conn = Db.getConnection();
        Statement statement = conn.createStatement();
        Statement statement1 = conn.createStatement();
        ResultSet userRs = statement.executeQuery(usernameSQL);
        ResultSet emailRs = statement1.executeQuery(emailSQL);

        int status = 0;
        if(!userRs.next()) {
            System.out.println("User is good");
        } else

        if(!emailRs.next()){
            System.out.println("Email is good!");
        }






        return 0;
    }



    public static void main(String[] args) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
        System.out.println(register("aisyah","123123", "syafiqhanafee@gmail.com"));
        //status 2 = username not available
        //status 1 = all success
        //status 0 = insert into db error
    }
}
