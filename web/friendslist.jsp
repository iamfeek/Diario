<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page import="friends.FriendsBean" %>
<%@ page import="DAO.Friend" %>
<%@ page import="java.util.List" %>
<c:choose>
    <c:when test="${sessionScope.loggedIn}">
        <% response.sendRedirect("/checkInstagram"); %>
    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>

<html>
<head>
    <link href="css/profile.css" rel="stylesheet">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
</head>
<body>
<a href="profile.jsp">Back to profile... </a>
<div id="snippetContent" style="padding-top:10px;">
    <div class="container">
        <div class="header">
            <h3 class="text-muted prj-name"><span class="fa fa-users fa-2x principal-title"></span> Friend zone</h3>
        </div>
        <form method="get" >
            <div class="jumbotron list-content">
                <ul class="list-group">
                    <li href="#" class="list-group-item title"> Your friend zone</li>
                    <sql:setDataSource
                            var="myDS"
                            driver="com.mysql.jdbc.Driver"
                            url="jdbc:mysql://localhost:3306/diario"
                            user="root" password="root"
                    />


                    <sql:query var="srh" dataSource="${myDS}">
                        SELECT * FROM friends WHERE locate(?, f_username)>0;
                        <sql:param value="${param.s}"/>

                    </sql:query>
                    <form id="search">
                        <input type="search" name="s" title="search" />
                        <button type="submit" > Search</button>
                    </form>
                    <c:forEach var="row" items="${srh.rows}">

                    <li href="#" class="list-group-item text-left">
                        <div class="media-body">
                            <a class="pull-left" href="#fakelink">
                                <img class="media-object img-circle" src="http://bootdey.com/img/Content/user_2.jpg">
                            </a>
                            <div class="row">
                                <div class="col-sm-4">
                                    <h2 class="media-heading"><a href="#fakelink" >${row.f_username}</a></h2>
                                    <p><i class="fa fa-map-marker"></i>${row.location}
                                        <br><small class="text-danger">${row.MF} Mutual Friends</small></p>
                                </div>

                                <div style="float: right; padding-right: 15px; width: 50%;">

                                    <a type="submit" class="btn btn-success btn-xs glyphicon glyphicon-ok" href="profile.jsp?friend=${row.fUNAME}"> View</a>

                                    <form method="post" action="/unfriend">
                                        <input type="hidden" name="unfriend" value="${row.id}"/>
                                        <button type="submit" class="btn btn-danger  btn-xs glyphicon glyphicon-remove"> Unfriend</button>
                                    </form>
                                    <form method="post" action="/message">
                                        <input type="hidden" name="message" value="${row.id}"/>
                                        <button type="submit" class="btn btn-info  btn-xs glyphicon glyphicon glyphicon-comment"> Message</button>
                                    </form>
                                </div>


                            </div>
                            </c:forEach>

                                <%
    FriendsBean fb = new FriendsBean();
    List<Friend> f = fb.getFriends();
    pageContext.setAttribute("f", f, PageContext.PAGE_SCOPE);
%>
                            <%--@elvariable id="f" type="java.util.List"--%>
                            <c:forEach var="friend" items ="${f}">
                    <li href="#" class="list-group-item text-left">
                        <div class="media-body">
                            <a class="pull-left" href="#fakelink">
                                <img class="media-object img-circle" src="http://bootdey.com/img/Content/user_2.jpg">
                            </a>
                            <div class="row">
                                <div class="col-sm-4">
                                    <h2 class="media-heading"><a href="#fakelink" >${friend.f_username}</a></h2>
                                    <p><i class="fa fa-map-marker"></i>${friend.location}
                                        <br><small class="text-danger">${friend.MF} Mutual Friends</small></p>
                                </div>

                                <div style="float: right; padding-right: 15px;">

                                    <form method="get" action="/view">
                                        <input type="hidden" id="view" name="view" value="${friend.id}"/>
                                        <button type="submit" class="btn btn-success btn-xs glyphicon glyphicon-ok" href="profile.jsp/view=${friend.username}" > View</button>
                                    </form>
                                    <form method="post" action="/unfriend">
                                        <input type="hidden" id="unfriend" name="unfriend" value="${friend.id}"/>
                                        <button type="submit" class="btn btn-danger  btn-xs glyphicon glyphicon-remove"> Unfriend</button>
                                    </form>
                                    <form method="post" >
                                        <input type="hidden" id="message" name="message" value="${friend.id}"/>
                                        <button type="submit" class="btn btn-info  btn-xs glyphicon glyphicon glyphicon-comment"> Message</button>
                                    </form>
                                </div>

                            </div>

                        </div><!-- /.row -->
                        <div class="break"></div>
                    </li>
                    </c:forEach>

                    <li href="#" class="list-group-item text-left">
                        <a class="btn btn-block btn-primary"><i class="glyphicon glyphicon-refresh"></i> Load more... </a>
                    </li>
                </ul>
            </div>
        </form>
    </div>
</div>


</body>
</html>