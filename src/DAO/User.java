package DAO;

import database.Db;

import java.sql.*;
import java.util.HashMap;

/**
 * Created by: Syafiq Hanafeegit
 * Dated: 17/11/15.
 */

public class User {
    private String username, email, salt, verifier;

    //GETTERS & SETTERS
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getVerifier() {

        return verifier;
    }

    //Constuctors
    public User(String username){
        this.username = username;
    }
    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    public User(String username, String email, String salt, String verifier){
        this.username = username;
        this.email = email;
        this.salt = salt;
        this.verifier = verifier;
    }

    public User(String username, String salt, String verifier){
        this.username = username;
        this.salt = salt;
        this.verifier = verifier;
    }

    //METHODS
//    public boolean login() {
//        String username = this.getUsername();
//        String password = this.getPassword();
//
//        Connection conn = Db.getConnection();
//        String query = "SELECT password FROM accounts where username = '" + username + "';";
//        System.out.println("Login Query: " + query);
////        System.out.println("Retrieved password: " + password);
//
//        Statement st = null;
//        try {
//            st = conn.createStatement();
//            ResultSet rs = st.executeQuery(query);
//
//            String retrievedPassword = null;
//            while (rs.next()) {
//                retrievedPassword = rs.getString("password");
//                System.out.println("Retrieved Password: " + retrievedPassword);
//
//                if (PasswordHash.validatePassword(password, retrievedPassword)) {
//                    //legit account
//                    System.out.println("login PASS");
//                    return true;
//                } else {
//                    System.out.println("login FAIL");
//                    return false;
//                }
//            }
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (InvalidKeySpecException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }

    public boolean register() {
        try {
            System.out.println("Registering User");
            String username = this.getUsername();
            String email = this.getEmail();
            String salt = this.getSalt();
            String verifier = this.getVerifier();

            if (checkIfUsernameExists(username) && checkifEmailExists(email)) {
                System.out.println("Writing to DB....");
                Connection conn = Db.getConnection();

                //generating the sqls and stuff before executeUpdate
                String sql = "INSERT INTO accounts (username, email_address, salt, verifier) VALUES (?, ?, ?, ?);";
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString(1, username);
                preparedStmt.setString(2, email);
                preparedStmt.setString(3, salt);
                preparedStmt.setString(4, verifier);

                int status = preparedStmt.executeUpdate();
                conn.close();

                if (status == 1) {
                    KeyGen.genAndStore(username);
                    System.out.println("register PASS");
                    return true;
                } else {
                    System.out.println("register FAIL");
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkIfUsernameExists(String username) throws SQLException {
        Connection conn = Db.getConnection();
        String query = "SELECT username FROM accounts where username = '" + username + "';";
        System.out.println("username check query: " + query);


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
        } else {
//            System.out.println(username + " FAIL");
            return false;
        }
    }

    public boolean checkifEmailExists(String email) throws SQLException {
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
        } else {
            System.out.println(email + " FAILED");
            return false;
        }
    }

    public HashMap<String, String> getSaltAndVerifier() throws SQLException {
        String username = this.getUsername();
        Connection conn = Db.getConnection();
        String query = "SELECT salt, verifier FROM accounts WHERE username = '" + username + "';";

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        String retrievedSalt = null;
        String retrievedVerifier = null;

        while(rs.next()){
            retrievedSalt = rs.getString("salt");
            retrievedVerifier = rs.getString("verifier");
        }

        if(retrievedSalt == null || retrievedVerifier == null){
            return null;
        } else {
            HashMap<String, String> saltAndVerifier = new HashMap<String, String>();
            saltAndVerifier.put("salt", retrievedSalt);
            saltAndVerifier.put("verifier", retrievedVerifier);
            return saltAndVerifier;
        }
    }
}
