<jsp:include page="header.jsp"></jsp:include>
<div style="padding: 30px; margin-left: 100px; margin-top: 50px">
    <h1 style="font-size: 30px; padding-bottom: 20px">Your Friends Images</h1>
    <%@ page import="DAO.DAOImages" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="DAO.Friends" %>
    <%
        ArrayList<String> friends = Friends.getFriendsList(session.getAttribute("username").toString());
        for (int i = 0; i < friends.size(); i++)    {
            ArrayList<Integer> ids = DAOImages.getImageIds(friends.get(i));
            if (!ids.isEmpty()) {
                out.println("<div><h3>" + friends.get(i) + "</h3></div>");
            }
            for (int j = 0; j < ids.size(); j++)  {
                out.println("<img width=\"1200\" src=\"getImage?imgid=" + ids.get(j) + "\">");
            }
        }
    %>
</div>