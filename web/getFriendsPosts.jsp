<%@ page import="DAO.DAOPost" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="post.Post" %>
<%@ page import="DAO.Friends" %><%
    ArrayList<Post> posts = new ArrayList<Post>();
    ArrayList<String> friends = Friends.getFriendsList(session.getAttribute("username").toString());
    for (int i = 0; i < friends.size(); i++)    {
        ArrayList<Post> shared = DAOPost.getSharedPosts(friends.get(i));
        for (int j = 0; j < shared.size(); j++) {
            posts.add(shared.get(j));
        }
    }
    for (int i = 0; i < posts.size(); i++)  {
        String post = "<div style=\"padding: 20px; padding-bottom:0px; font-size: 20px\">";
        post += posts.get(i).getUsername();
        post += "</div>";
        post += "<div style=\"padding: 20px\" class=\"RSA_AES\">";
        post += posts.get(i).getText();
        if (posts.get(i).getEncrypted() && posts.get(i).getShared())    {
            post += ";AES:";
            post += DAOPost.getAESKey(posts.get(i).getPostid(), session.getAttribute("username").toString());
        }
        post += "</div>";
        out.println(post);
    }
%>