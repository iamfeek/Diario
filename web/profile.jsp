<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="nono" type="DAO.Friend"--%>
<c:choose>
    <c:when test="${sessionScope.loggedIn}">
    </c:when>
    <c:otherwise>
        <% response.sendRedirect("/signin"); %>
    </c:otherwise>
</c:choose>

<html>

<jsp:include page="header.jsp">
    <jsp:param name="profile" value="active"></jsp:param>
</jsp:include>
<div style="margin-top: 60px;">
    <jsp:include page="findFriends.jsp" flush="true"/>
</div>
<div class="container" style="margin-top: 60px;">
    <div class="row">
        <div class="col-md-3">
            <%--Profile Stuff--%>
            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading" style="text-align: center">
                        <strong>${nono.username}'s</strong> Statistics
                    </div>
                    <div class="panel-body">
                        <div class="col-md-4" style="">
                            <img src="img/iamfeek.jpg" alt="${nono.username}'s Profile Picture" height="80px"
                                 width="70px">
                        </div>
                        <div class="col-md-8" style="">
                            Location: ${nono.location}<br>
                            # of Posts: ${nono.posts}<br>
                            # of Friends: ${nono.friends}<br>
                        </div>
                    </div>
                </div>
            </div>
            <%--friends--%>
            <div class="row">
                <div class="panel">
                    <jsp:include page="friendslist.jsp" flush="true"/>
                </div>
            </div>
            <%--End Profile Stuff--%>

            <%--Key Management Stuff--%>
            <div class="row">
                <div class="panel panel-danger">
                    <div class="panel-heading" style="text-align: center;">Encryption Key Management</div>
                    <div class="panel-body" style="text-align: center">
                        <div style="line-height: 30px; border: 1px solid #CCCCCC; padding: 5px; border-radius: 5px">
                            Import Key<br>
                            <input type="file" id="fileinput"/>
                            <div>
                                <input id="passphrase" type="password"style="display:none; margin: 5px; line-height: 20px" placeholder="Password" onkeypress="testImport(event)"/>
                            </div>
                        </div>
                        <br>
                        <input type="button" class="btn btn-default navbar-btn"
                               onclick="downloadKey()"
                               value="Save Key"/>
                    </div>
                </div>
            </div>
            <%--End Key Management Stuff--%>
        </div>
        <div class="col-md-9" style="border: 1px solid">
            <%--<jsp:include page="viewPosts.jsp"></jsp:include>--%>
            List of all our posts
        </div>
    </div>
</div>
<script type="text/javascript">
    var keycontents = "";
    function readKeyFile(evt) {
        var f = evt.target.files[0];

        if (f) {
            var r = new FileReader();
            r.onload = function(e) {
                keycontents = e.target.result;
                document.getElementById("passphrase").style.display = "";
                document.getElementById("passphrase").focus();
            }
            r.readAsText(f);
        } else {
            alert("Failed to load file");
        }
    }

    document.getElementById('fileinput').addEventListener('change', readKeyFile, false);

    function testImport(e) {
        if (e.keyCode == 13) {
            var keys = keycontents.split("          ");
            var success = false;
            try {
                var decryptedKey = decryptKey(keys[0], document.getElementById("passphrase").value);
                success = true;
            }
            catch(error)    {
                alert("Wrong password, import failed");
                document.getElementById("fileinput").value = "";
                document.getElementById("passphrase").value = "";
                document.getElementById("passphrase").style.display = "none";
            }
            if (success)
                importKey(keys[0], decryptedKey, keys[1]);
        }
    }

    function importKey(encryptedPrv, prvKey, pubKey)    {
        localStorage.setItem(sessionStorage.getItem("username") + "_prvKey", encryptedPrv);
        localStorage.setItem(sessionStorage.getItem("username") + "_pubKey", pubKey);
        sessionStorage.setItem("prvKey", prvKey);
        sessionStorage.setItem("pubKey", pubKey);
        alert("Key Successfully Imported!");
        document.getElementById("fileinput").value = "";
        document.getElementById("passphrase").value = "";
        document.getElementById("passphrase").style.display = "none";
    }
</script>
<script type="text/javascript" src="js/security/key-handler.js"></script>
<script type="text/javascript" src="js/security/aes.js"></script>
<script type="text/javascript" src="js/security/aes-handler.js"></script>
<script type="text/javascript" src="js/security/mode-ecb-min.js"></script>
</html>