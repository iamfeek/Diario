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
                        <input type="button" class="btn btn-default navbar-btn"
                               onclick="alert(localStorage.getItem('prvKey'))"
                               value="View Key"/>
                        <br>
                        <input type="button" class="btn btn-default navbar-btn"
                               onclick="saveTextAsFile()"
                               value="Download Key"/>
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
    function saveTextAsFile() {
        var textToWrite = localStorage.getItem("prvKey");
        var textFileAsBlob = new Blob([textToWrite], {type: 'text/plain'});
        var fileNameToSaveAs = "key.txt"

        var downloadLink = document.createElement("a");
        downloadLink.download = fileNameToSaveAs;
        downloadLink.innerHTML = "Download File";
        if (window.webkitURL != null) {
            // Chrome allows the link to be clicked
            // without actually adding it to the DOM.
            downloadLink.href = window.webkitURL.createObjectURL(textFileAsBlob);
        }
        else {
            // Firefox requires the link to be added to the DOM
            // before it can be clicked.
            downloadLink.href = window.URL.createObjectURL(textFileAsBlob);
            downloadLink.onclick = destroyClickedElement;
            downloadLink.style.display = "none";
            document.body.appendChild(downloadLink);
        }

        downloadLink.click();
    }

    function destroyClickedElement(event) {
        document.body.removeChild(event.target);
    }
</script>
</html>