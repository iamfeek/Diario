package diary;

import DAO.pages;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by NH on 12/7/2015.
 */
public class pageController {

    public List<pages> getPages() throws SQLException, ClassNotFoundException{
        List<pages> list = new ArrayList<pages>();
        Connection con = null;
        Statement stmt = null;
        String connectionURL = "jdbc:mysql://localhost:3306/diario";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(connectionURL, "root", "root");
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT date, title, content FROM pages;");
            while (rs.next()) {
                pages p = new pages();
                p.setDate(rs.getString(1));
                p.setTitle(rs.getString(2));
                p.setContent(rs.getString(3));

                list.add(p);
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
        return list;
    }
}
