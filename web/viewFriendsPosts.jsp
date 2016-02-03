<jsp:include page="header.jsp">
    <jsp:param name="post" value="active"></jsp:param>
    <jsp:param name="postViewFriendPosts" value="active"></jsp:param>
</jsp:include>
    <script type="text/javascript" src="js/security/rsa-bundle.js"></script>
    <script type="text/javascript" src="js/security/key-handler.js"></script>
    <script type="text/javascript" src="js/security/aes-handler.js"></script>
    <script type="text/javascript" src="js/security/aes.js"></script>
    <script type="text/javascript" src="js/security/mode-ecb-min.js"></script>

    <div style="padding: 30px; margin-left: 100px; margin-top: 50px">
        <h1 style="font-size: 30px;">Your Friends' Posts</h1>
        <%@ page import="DAO.DAOPost" %>
        <%@ page import="java.util.ArrayList" %>
        <%@ page import="post.Post" %>
        <%@ page import="DAO.Friends" %>
        <%@ page import="java.util.Collections" %>
        <%@ page import="java.text.DateFormat" %><%
        ArrayList<Post> posts = new ArrayList<Post>();
        ArrayList<String> friends = Friends.getFriendsList(session.getAttribute("username").toString());
        for (int i = 0; i < friends.size(); i++)    {
            ArrayList<Post> shared = DAOPost.getSharedPosts(friends.get(i));
            for (int j = 0; j < shared.size(); j++) {
                posts.add(shared.get(j));
            }
        }
        if (posts.size() == 0) {
            out.println("<div style=\"padding: 20px; padding-bottom: 10px\">There are no posts by your friends</div>");
        }
        Collections.sort(posts);
        for (int i = 0; i < posts.size(); i++)  {
            String post = "<div style=\"padding: 20px; padding-bottom:0px; font-size: 20px\">";
            post += posts.get(i).getUsername();
            post += "</div><div style=\"padding: 20px; padding-bottom: 10px\">";
            post += DateFormat.getDateTimeInstance().format(posts.get(i).getDate());
            if (posts.get(i).getEncrypted())    {
                post += "</div><div style=\"padding: 20px; padding-top: 0px; border-bottom: 1px solid #CCCCCC\" class=\"RSA_AES\">";
            }
            else    {
                post += "</div><div style=\"padding: 20px; padding-top: 0px; border-bottom: 1px solid #CCCCCC\">";
            }
            post += posts.get(i).getText();
            if (posts.get(i).getEncrypted() && posts.get(i).getShared())    {
                post += ";AES:";
                post += DAOPost.getAESKey(posts.get(i).getPostid(), session.getAttribute("username").toString());
            }
            post += "</div>";
            out.println(post);
        }
    %>
        <script type="text/javascript">
            //decrypt all RSAxAES Posts
            var rsaAesPosts = document.getElementsByClassName("RSA_AES");
            for (var i = 0; i < rsaAesPosts.length; i++)    {
                var fulltext = rsaAesPosts[i].innerText;
                var splittext = fulltext.split(";AES:");
                var AESKey = decryptString(splittext[1]);
                rsaAesPosts[i].innerText = decryptAES(splittext[0], AESKey);
            }
        </script>
    </div>