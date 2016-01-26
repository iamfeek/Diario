<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<div class="container" style="margin-top: 60px;">

    <h3> ${nono.username} </h3>
    <p> Location: ${nono.location} <br>
        ${nono.MF} Mutual Friends </p>
    <p>
        ${nono.username} has created ${nono.posts} posts.<br>
        ${nono.username} made ${nono.friends} friends.
    </p>
    <script type="text/javascript">
        function saveTextAsFile()
        {
            var textToWrite = localStorage.getItem("prvKey");
            var textFileAsBlob = new Blob([textToWrite], {type:'text/plain'});
            var fileNameToSaveAs = "key.txt"

            var downloadLink = document.createElement("a");
            downloadLink.download = fileNameToSaveAs;
            downloadLink.innerHTML = "Download File";
            if (window.webkitURL != null)
            {
                // Chrome allows the link to be clicked
                // without actually adding it to the DOM.
                downloadLink.href = window.webkitURL.createObjectURL(textFileAsBlob);
            }
            else
            {
                // Firefox requires the link to be added to the DOM
                // before it can be clicked.
                downloadLink.href = window.URL.createObjectURL(textFileAsBlob);
                downloadLink.onclick = destroyClickedElement;
                downloadLink.style.display = "none";
                document.body.appendChild(downloadLink);
            }

            downloadLink.click();
        }

        function destroyClickedElement(event)
        {
            document.body.removeChild(event.target);
        }
    </script>
    <input type="button" class="btn btn-default navbar-btn" onclick="saveTextAsFile()"
           value="Export Key"/>
    <h3><span class="h3 text">Find friends</span></h3>
    <form id="search">
        <input type="search" name="search"   />
        <button type="submit"> Search</button>
    </form>
</div>
<c:forEach var="row" items="${srh.rows}">
    <div class="container ">

        <div class="row">
            <div class="col-xs-12 col-sm-6 col-md-6">
                <div class="well well-sm">
                    <div class="row">
                        <div class="col-sm-6 col-md-4">
                            <img src="img/iamfeek.jpg" alt="" class="img-rounded img-responsive"/>
                        </div>
                        <div class="col-sm-6 col-md-8">
                            <h4>
                                ${nono.username}</h4>
                            <small><cite title="San Francisco, USA">${nono.location} <i
                                    class="glyphicon glyphicon-map-marker">
                            </i></cite></small>
                            <p>
                                <i class="fa fa-pencil-square-o"></i> ${nono.posts} posts
                                <br/>
                                <i class="fa fa-users"></i> ${nono.friends} users
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <input type="button" class="btn btn-default navbar-btn" onclick="alert(localStorage.getItem('prvKey'))"
               value="Export Key"/>
        <%--<h3><span class="h3 text">Find friends</span></h3>--%>
        <%--<form id="search">--%>
        <%--<input type="search" name="search" />--%>
        <%--<button type="submit"> Search</button>--%>
        <%--</form>--%>
    <c:forEach var="row" items="${srh.rows}">
    <div class="container">
        <li href="#" class="list-group-item text-left">
            <div class="media-body">
                <a class="pull-left" href="#fakelink">
                    <img class="media-object img-circle" src="http://bootdey.com/img/Content/user_2.jpg">
                </a>
                <div class="row">
                    <div class="col-sm-4">
                        <h2 class="media-heading"><a href="#fakelink">${row.username}</a></h2>
                        <p><i class="fa fa-map-marker"></i>${row.location}
                            <br>
                            <small class="text-danger">${row.MF} Mutual Friends</small>
                        </p>
                    </div>

                    <div style="float: right; padding-right: 15px;">

                        <form method="get" action="/view">
                            <input type="hidden" id="view" name="view" value="${row.id}"/>
                            <button type="submit" class="btn btn-success btn-xs glyphicon glyphicon-ok"
                                    href="profile.jsp/view=${row.username}"> View
                            </button>
                        </form>

                        <form name="lol" method="get" action="/addFriend">
                            <input type="hidden" name="id" value="${fn:escapeXml(row.id)}"/>
                            <input type="hidden" name="fUNAME" value="${fn:escapeXml(row.username)}"/>
                            <input type="hidden" name="location" value="${fn:escapeXml(row.location)}"/>
                            <input type="hidden" name="MF" value="${fn:escapeXml(row.MF)}"/>
                            <button type="submit" class="btn btn-default  btn-xs glyphicon glyphicon-user"> + Add
                                Friend
                            </button>
                        </form>

                    </div>

                </div>
            </div><!-- /.row -->

            <div class="break"></div>
        </li>
    </div>
</div>
</c:forEach>
<div class="break"></div>
</body>
</html>