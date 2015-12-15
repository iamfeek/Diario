package diary;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by lenovo on 12/6/2015.
 */

public class pageServlet extends HttpServlet {

// PreparedStatement ps = connection.prepareStatement("Select DATE_FORMAT(CURDATE(),'%d %M %Y') AS today");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw =response.getWriter();
        String connectionURL = "jdbc:mysql://localhost:3306/diario";
        Connection connection;

        //Post
        try {
            String Title = request.getParameter("title");
            String Content = request.getParameter("content");

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL, "root", "root");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO pages (title, content, date) VALUES (?,?, DATE_FORMAT(CURDATE(),'%W, %d %M %Y'))");
            ps.setString(1, Title);
            ps.setString(2, Content);
            int i = ps.executeUpdate();

            if(i!=0) {
                response.sendRedirect("diary.jsp");
            }
            else{
                pw.println("Please insert details!");
            }
        }catch (Exception e){
            pw.println(e);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
