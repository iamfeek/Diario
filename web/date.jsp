<%@ page import="java.sql.*" %>
<%@ page import="database.Db" %>

<%

    try

    {
        Connection conn = Db.getConnection();
        Statement stmt;
        ResultSet rs;

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
