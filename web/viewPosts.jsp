<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Diario">

<jsp:attribute name="head_area">
    <script type="text/javascript" src="js/security/rsa-bundle.js"></script>
    <script type="text/javascript" src="js/security/key-handler.js"></script>
    <script type="text/javascript" src="js/security/aes-handler.js"></script>
    <script type="text/javascript" src="js/security/aes.js"></script>
    <script type="text/javascript" src="js/security/mode-ecb-min.js"></script>
</jsp:attribute>

<jsp:attribute name="body_area">
    <div style="padding: 30px; margin-left: 100px">
        <h1 style="font-size: 30px;">Your own posts</h1>
        <jsp:include page="getOwnPosts.jsp"></jsp:include>
        <script type="text/javascript">
            //decrypt all RSA posts
            var rsaPosts = document.getElementsByClassName("RSA");
            for (var i = 0; i < rsaPosts.length; i++)   {
                rsaPosts[i].innerText = decryptString(rsaPosts[i].innerText);
            }

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
</jsp:attribute>
</t:layout>