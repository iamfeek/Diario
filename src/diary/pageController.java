package diary;


import DAO.pages;
import database.Db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NH on 12/7/2015.
 */
public class pageController {

    public List<pages> getPages() throws SQLException, ClassNotFoundException{
        List<pages> list = new ArrayList<pages>();
        Connection con = Db.getConnection();
        Statement stmt = null;

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pages;");
            while (rs.next()) {
                pages p = new pages();
                p.setDate(rs.getString(4));
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
