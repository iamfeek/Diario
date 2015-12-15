<%@ page import="java.sql.*" %>

<%

    try

    {

        String connectionURL = "jdbc:mysql://localhost:3306/diario";
        Connection conn;
        Statement stmt;
        ResultSet rs;

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(connectionURL, "root", "root");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("Select DATE_FORMAT(CURDATE(),'%W, %d %M %Y') AS today;"); %>

     <%   while(rs.next()) { %>
         <% out.println("<div style=\"margin: auto; width: 37%; border: 3px solid #2f889a; padding: 10px;\"><h3 style=\"font-size: 30px; font-family: 'Andy';\">"+rs.getString("today")+"</h3>"); %>
       <% }

    }

    catch(Exception ee)

    {
        System.out.println(ee);
    }

%>
