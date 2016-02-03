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
    private int id;

    //GETTERS & SETTERS

    public int getId(){return id;}


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
    public User(String username) throws SQLException {
        this.username = username;
        this.email = getEmailFromUsername(username);
        this.id = getIdFromUsername(username);
    }

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    public User(int id, String username){
        this.id = id;
        this.username = username;
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

    private String getEmailFromUsername(String username) throws SQLException {
        Connection conn = Db.getConnection();
        String sql = "select email_address from accounts where username = ?";
        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        String retrievedEmail = null;
        while(rs.next()){
            retrievedEmail = rs.getString("email_address");
        }

        return retrievedEmail;
    }

    private int getIdFromUsername(String username) throws SQLException {
        Connection conn = Db.getConnection();
        String sql = "select userid from accounts where username = ?";
        PreparedStatement pstmt = null;
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        int retrievedUserid = 0;
        while(rs.next()){
            retrievedUserid = rs.getInt("userid");
        }

        return retrievedUserid;
    }

    public boolean createProfile(){
        int id = this.getId();
        String username = this.getUsername();

        try{
            Connection conn = Db.getConnection();
            String sql = "INSERT into PROFILES(id, username) VALUES(?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, username);
            System.out.println("Create sql: " + pstmt.toString());
            pstmt.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

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
        System.out.println(query);

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        String retrievedSalt = null;
        String retrievedVerifier = null;

        while(rs.next()){
            retrievedSalt = rs.getString("salt");
            retrievedVerifier = rs.getString("verifier");
        }

        if(retrievedSalt == null || retrievedVerifier == null){
            System.out.println("Returning null");
            return null;
        } else {
            HashMap<String, String> saltAndVerifier = new HashMap<String, String>();
            saltAndVerifier.put("salt", retrievedSalt);
            saltAndVerifier.put("verifier", retrievedVerifier);
            return saltAndVerifier;
        }
    }
}
