<%--@elvariable id="nono" type="DAO.Friend"--%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <input type="button" class="btn btn-default navbar-btn" onclick="alert(localStorage.getItem('prvKey'))"
           value="Export Key"/>
    <h3><span class="h3 text">Find friends</span></h3>
    <form id="search">
        <input type="search" name="search"   />
        <button type="submit"> Search</button>
    </form>
</div>
<c:forEach var="row" items="${srh.rows}">
    <div class="container ">

        <li href="#" class="list-group-item text-left">
            <div class="media-body">
                <a class="pull-left" href="#fakelink">
                    <img class="media-object img-circle" src="http://bootdey.com/img/Content/user_2.jpg">
                </a>
                <div class="row">
                    <div class="col-sm-4">
                        <h2 class="media-heading"><a href="#fakelink" >${row.username}</a></h2>
                        <p><i class="fa fa-map-marker"></i>${row.location}
                            <br><small class="text-danger">${row.MF} Mutual Friends</small></p>
                    </div>

                    <div style="float: right; padding-right: 15px;">

                        <form method="get" action="/view">
                            <input type="hidden" id="view" name="view" value="${row.id}"/>
                            <button type="submit" class="btn btn-success btn-xs glyphicon glyphicon-ok" href="profile.jsp/view=${row.username}" > View</button>
                        </form>

                        <form name="lol" method="get" action="/addFriend" >
                            <input type="hidden" name="id" value="${fn:escapeXml(row.id)}" />
                            <input type="hidden" name="fUNAME" value="${fn:escapeXml(row.username)}" />
                            <input type="hidden" name="location" value="${fn:escapeXml(row.location)}"/>
                            <input type="hidden" name="MF" value="${fn:escapeXml(row.MF)}"/>
                            <button type="submit" class="btn btn-default  btn-xs glyphicon glyphicon-user"> + Add Friend</button>
                        </form>

                    </div>

                </div>
            </div><!-- /.row -->

            <div class="break"></div>
        </li>
    </div>
</c:forEach>
<div class="break"></div>
</body>
</html>