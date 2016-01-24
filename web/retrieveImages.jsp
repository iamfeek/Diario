<%@ page import="DAO.DAOImages" %>
<%@ page import="java.util.ArrayList" %>
<%
    ArrayList<Integer> ids = DAOImages.getImageIds(session.getAttribute("username").toString());
    for (int i = 0; i < ids.size(); i++)  {
        out.println("<img width=\"1200\" src=\"getImage?imgid=" + ids.get(i) + "\">");
    }
%>