<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="friends.FriendsBean" %>
<%@ page import="DAO.Friend" %>
<%@ page import="java.util.List" %>


<html>
<head>
    <link href="css/profile.css" rel="stylesheet">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
</head>
<body>
<div id="snippetContent" style="padding-top:10px;">

        <form method="get" >
            <div class=" list-content">
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
                    <li href="#" class="list-group-item text-left">
                        <div class="media-body">

                    <form id="search" class="input-group" >
                        <input type="search" name="s" title="search" class="form-control" placeholder="Search & press 'Enter'" />
                    </form>

                            </div>
                        </li>
                    <c:forEach var="row" items="${srh.rows}">
                        <li href="#" class="list-group-item text-left">
                            <div class="media-body">
                                <form method="get" action="/view">
                                    <input type="hidden" name="view" value="${fn:escapeXml(row.id)}"/>
                                    <button class="pull-left img-thumbnail img-circle" style="overflow: hidden; width: 50px; height: 50px;" >
                                        <img class="media-object img-circle pull-left" style="margin-left: -5px; margin-top: -4px;width: 50px; height: 50px;" src="http://bootdey.com/img/Content/user_2.jpg" >
                                    </button>
                                </form>
                                <div class="row">
                                        <form method="get" action="/view">
                                        <input type="hidden" name="view" value="${fn:escapeXml(row.id)}"/>
                                    <div class="col-sm-5">
                                            <p class="media-heading h3"><button class="btn-link"  >${row.f_username}</button></p>
                                        <p class="text-info" style="font-size: 12px;"><i class="fa fa-map-marker"></i>${row.location}
                                            <br><small class="text-danger">&nbsp;${row.MF} Mutual Friends</small></p>
                                    </div>
                                        </form>


                                    <div style="float: right; margin-right: 15px;">

                                        <form method="get" action="/view">
                                            <input type="hidden" name="view" value="${row.id}"/>
                                            <button type="submit" class="btn btn-success btn-xs glyphicon glyphicon-ok" href="profile.jsp/view=${row.username}" > View</button>
                                        </form>
                                        <form method="post" action="/unfriend">
                                            <input type="hidden" name="unfriend" value="${row.id}"/>
                                            <button type="submit" class="btn btn-danger  btn-xs glyphicon glyphicon-remove"> Unfriend</button>
                                        </form>
                                    </div>

                                </div>

                            </div><!-- /.row -->

                        </li>
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
                                        <form method="get" action="/view">
                                            <input type="hidden" name="view" value="${fn:escapeXml(friend.id)}"/>
                                            <button class="pull-left img-thumbnail img-circle" style="overflow: hidden; width: 50px; height: 50px;" >
                                                <img class="media-object img-circle pull-left" style="margin-left: -5px; margin-top: -4px;width: 50px; height: 50px;" src="http://bootdey.com/img/Content/user_2.jpg" >
                                            </button>
                                        </form>
                                        <div class="row">
                                            <form method="get" action="/view">
                                                <input type="hidden" name="view" value="${fn:escapeXml(friend.id)}"/>
                                                <div class="col-sm-5">
                                                    <p class="media-heading h3"><button class="btn-link"  >${friend.f_username}</button></p>
                                                    <p class="text-info" style="font-size: 12px;"><i class="fa fa-map-marker"></i>${friend.location}
                                                        <br><small class="text-danger">&nbsp;${friend.MF} Mutual Friends</small></p>
                                                </div>
                                            </form>


                                            <div style="float: right; margin-right: 15px;">

                                                <form method="get" action="/view">
                                                    <input type="hidden" name="view" value="${friend.id}"/>
                                                    <button type="submit" class="btn btn-success btn-xs glyphicon glyphicon-ok" href="profile.jsp/view=${friend.username}" > View</button>
                                                </form>
                                                <form method="post" action="/unfriend">
                                                    <input type="hidden" name="unfriend" value="${friend.id}"/>
                                                    <button type="submit" class="btn btn-danger  btn-xs glyphicon glyphicon-remove"> Unfriend</button>
                                                </form>
                                            </div>

                                        </div>

                                    </div><!-- /.row -->

                                </li>
                    </c:forEach>

                </ul>
            </div>
        </form>

</div>


</body>
</html>