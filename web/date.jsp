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
<div class="container"><h2 class="special-text">
         <% out.println(rs.getString("today")); %>
    </h2></div>
       <% }

    }

    catch(Exception ee)

    {
        System.out.println(ee);
    }

%>
