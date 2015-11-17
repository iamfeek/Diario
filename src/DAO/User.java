package DAO;

import authentication.PasswordHash;
import database.Db;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

/**
 * Created by: Syafiq Hanafee
 * Dated: 17/11/15.
 */
public class User {
    private String username, password, email;

    //GETTERS & SETTERS
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    //Constuctors
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //METHODS
    public boolean login() throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
        String username = this.getUsername();
        String password = this.getPassword();
        if (!checkIfUsernameExists(username)) {
            Connection conn = Db.getConnection();
            String query = "SELECT password FROM accounts where username = '" + username + "';";
            System.out.println("Login Query: " + query);

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);


            String retrievedPassword = null;
            while (rs.next()) {
                retrievedPassword = rs.getString("password");
            }
            conn.close();

            if (PasswordHash.validatePassword(password, retrievedPassword)) {
                //legit account
                System.out.println("login PASS");
                return true;
            } else {
                System.out.println("login FAIL");
                return false;
            }

//            response.setContentType("text/html");
//            response.getWriter().write("LEGIT");

        }
        return false;
    }

    public boolean register() {
        try {
            System.out.println("Registering User");
            String username = this.getUsername();
            String password = this.getPassword();
            String email = this.getEmail();

            if (checkIfUsernameExists(username) && checkifEmailExists(email)) {
                System.out.println("Writing to DB....");
                Connection conn = Db.getConnection();
                String hashedpassword = PasswordHash.createHash(password);

                //generating the sqls and stuff before executeUpdate
                String sql = "INSERT INTO accounts (username, password, email_address) VALUES (?, ?, ?);";
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, username);
                preparedStmt.setString(2, hashedpassword);
                preparedStmt.setString(3, email);

                int status = preparedStmt.executeUpdate();
                conn.close();

                if (status == 1) {
                    System.out.println("register PASS");
                    return true;
                }else {
                    System.out.println("register FAIL");
                    return false;
                }
            } else{
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static boolean checkIfUsernameExists(String username) throws SQLException {
        Connection conn = Db.getConnection();
        String query = "SELECT username FROM accounts where username = '" + username + "';";
//        System.out.println("username check query: " + query);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);


        String retrievedUsername = null;
        while (rs.next()) {
            retrievedUsername = rs.getString("username");
        }

//        System.out.println("Retrieved username: " + retrievedUsername);
        if (retrievedUsername == null) {
//            System.out.println(username + " PASS");
            return true;
        } else{
//            System.out.println(username + " FAIL");
            return false;
        }
    }

    static boolean checkifEmailExists(String email) throws SQLException {
        Connection conn = Db.getConnection();
        String query = "SELECT email_address FROM accounts where email_address = '" + email + "';";
//        System.out.println("Email check query: " + query);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);


        String retrievedEmail = null;
        while (rs.next()) {
            retrievedEmail = rs.getString("email_address");
        }
//        System.out.println("Retrieved email: " + retrievedEmail);

        if (retrievedEmail == null) {
            System.out.println("Email: " + email + " PASS");
            return true;
        } else{
            System.out.println(email + " FAILED");
            return false;
        }
    }

}
