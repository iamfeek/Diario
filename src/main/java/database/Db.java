package database;

import java.sql.*;
/**
 * Created by IamFeeK on 14/11/2015.
 */
public class DB {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/diario";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";

    static Statement stmt = null;
    static Connection conn = null;

    public static Connection getConnection(){
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally{
            return conn;
        }
    }

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
