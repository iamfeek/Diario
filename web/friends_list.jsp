<%@ page import="DAO.Friends"%>
<%@ page import="java.util.ArrayList" %>
<%
    String str = "";
    ArrayList friends = Friends.getFriendsList(session.getAttribute("username").toString());
    for (int i = 0; i < friends.size(); i++)    {
        str += "\n<option value=\"";
        str += friends.get(i);
        str += "\">";
    }
    out.println(str);
%>