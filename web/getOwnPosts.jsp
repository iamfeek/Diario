<%@ page import="DAO.DAOPost" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="post.Post" %><%
    ArrayList<Post> posts = DAOPost.getPosts(session.getAttribute("username").toString());
    for (int i = 0; i < posts.size(); i++)  {
        String post = "<div style=\"padding: 20px\"";
        if (posts.get(i).getEncrypted() && posts.get(i).getShared())
            post += " class=\"RSA_AES\"";
        else if (posts.get(i).getEncrypted())
            post += " class=\"RSA\"";
        post += '>';
        post += posts.get(i).getText();
        if (posts.get(i).getEncrypted() && posts.get(i).getShared())    {
            post += ";AES:";
            post += DAOPost.getAESKey(posts.get(i).getPostid(), session.getAttribute("username").toString());
        }
        post += "</div>";
        out.println(post);
    }
%>