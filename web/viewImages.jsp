<jsp:include page="header.jsp"></jsp:include>
<div style="padding: 30px; margin-left: 100px; margin-top: 50px">
    <h1 style="font-size: 30px; padding-bottom: 20px">Your Images</h1>
    <%@ page import="DAO.DAOImages" %>
    <%@ page import="java.util.ArrayList" %>
    <%
        ArrayList<Integer> ids = DAOImages.getImageIds(session.getAttribute("username").toString());
        for (int i = 0; i < ids.size(); i++)  {
            out.println("<img width=\"1200\" src=\"getImage?imgid=" + ids.get(i) + "\">");
        }
    %>
</div>