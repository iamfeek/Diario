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
<div class="container" style="margin-top: 50px;">
    <div class="row">
        <div class="col-md-1" style="border: 1px solid;">
            <img src="img/iamfeek.jpg" alt="${nono.username}'s Profile Picture" height="80px" width="80px">
        </div>
        <div class="col-md-3" style="border: 1px solid;">
            <strong>${nono.username}</strong> <br>
            Location: ${nono.location}<br>
            # of Posts: ${nono.posts}<br>
            # of Friends: ${nono.friends}<br>
        </div>
        <div class="col-md-8" style="border: 1px solid; text-align: center;">
            <jsp:include page="viewPosts.jsp"></jsp:include>
        </div>
    </div>
    <input type="button" class="btn btn-default navbar-btn" onclick="alert(localStorage.getItem('prvKey'))"
           value="Export Key"/>
</div>
<div class="break"></div>
</body>
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