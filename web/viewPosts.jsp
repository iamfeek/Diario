<script type="text/javascript" src="js/security/rsa-bundle.js"></script>
<script type="text/javascript" src="js/security/key-handler.js"></script>
<script type="text/javascript" src="js/security/aes-handler.js"></script>
<script type="text/javascript" src="js/security/aes.js"></script>
<script type="text/javascript" src="js/security/mode-ecb-min.js"></script>
<jsp:include page="header.jsp">
    <jsp:param name="post" value="active"></jsp:param>
    <jsp:param name="postView" value="active"></jsp:param>
</jsp:include>
<div style="padding: 30px; margin-left: 100px; margin-top: 50px">
    <h1 style="font-size: 30px;">Your Diary</h1>
    <%@ page import="DAO.DAOPost" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="post.Post" %>
    <%@ page import="java.text.DateFormat" %>
    <%@ page import="java.util.Collections" %>
    <%
        ArrayList<Post> posts = DAOPost.getPosts(session.getAttribute("username").toString());
        Collections.sort(posts);
        if (posts.size() == 0) {
            out.println("<div style=\"padding: 20px; padding-bottom: 10px\">You do not have any posts</div>");
        }
        for (int i = 0; i < posts.size(); i++) {
            String post = "<div style=\"padding: 20px; padding-bottom: 10px\">";
            post += DateFormat.getDateTimeInstance().format(posts.get(i).getDate());
            post += "</div>";
            post += "<div style=\"padding: 20px; padding-top: 0px; border-bottom: 1px solid #CCCCCC\"";
            if (posts.get(i).getEncrypted() && posts.get(i).getShared())
                post += " class=\"RSA_AES\"";
            else if (posts.get(i).getEncrypted())
                post += " class=\"RSA\"";
            post += '>';
            post += posts.get(i).getText();
            if (posts.get(i).getEncrypted() && posts.get(i).getShared()) {
                post += ";AES:";
                post += DAOPost.getAESKey(posts.get(i).getPostid(), session.getAttribute("username").toString());
            }
            post += "</div>";
            out.println(post);
        }
    %>
    <script type="text/javascript">
        //decrypt all RSA posts
        var rsaPosts = document.getElementsByClassName("RSA");
        for (var i = 0; i < rsaPosts.length; i++) {
            rsaPosts[i].innerText = decryptString(rsaPosts[i].innerText);
        }

        //decrypt all RSAxAES Posts
        var rsaAesPosts = document.getElementsByClassName("RSA_AES");
        for (var i = 0; i < rsaAesPosts.length; i++) {
            var fulltext = rsaAesPosts[i].innerText;
            var splittext = fulltext.split(";AES:");
            var AESKey = decryptString(splittext[1]);
            rsaAesPosts[i].innerText = decryptAES(splittext[0], AESKey);
        }
    </script>
</div>