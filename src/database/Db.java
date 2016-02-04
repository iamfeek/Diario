package database;

import java.sql.*;
/**
 * Created by IamFeeK on 14/11/2015.
 */
public class Db {
    static Statement stmt = null;
    static Connection conn = null;

    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/diario","root", "root");
            return conn;
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
//port: 3306 password: root


    public static void main(String[] args) throws SQLException{
        conn = getConnection();
        //STEP 4: Execute a query
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT userid, username, password FROM accounts";
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("userid");
            String first = rs.getString("username");
            String last = rs.getString("password");

            //Display values
            System.out.print("ID: " + id);
            System.out.print(", Username: " + first);
            System.out.println(", Password: " + last);
        }
        rs.close();
        stmt.close();
        conn.close();

    }

}
