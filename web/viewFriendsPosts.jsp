<jsp:include page="header.jsp">
    <jsp:param name="post" value="active"></jsp:param>
</jsp:include>
    <script type="text/javascript" src="js/security/rsa-bundle.js"></script>
    <script type="text/javascript" src="js/security/key-handler.js"></script>
    <script type="text/javascript" src="js/security/aes-handler.js"></script>
    <script type="text/javascript" src="js/security/aes.js"></script>
    <script type="text/javascript" src="js/security/mode-ecb-min.js"></script>

    <div style="padding: 30px; margin-left: 100px; margin-top: 50px">
        <h1 style="font-size: 30px;">Your Friends' Posts</h1>
        <jsp:include page="getFriendsPosts.jsp"></jsp:include>
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