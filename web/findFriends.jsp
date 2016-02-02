
<%--@elvariable id="nono" type="DAO.Friend"--%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <%--all custom js/css for this page belongs here.--%>
    <link href="css/profile.css" rel="stylesheet">
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

</head>

<body>
<jsp:include page="date.jsp" flush="true"/>
<sql:setDataSource
        var="myDS"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:8888/diario"
        user="root" password="narumi"
/>

<sql:query var="srh" dataSource="${myDS}">
    SELECT * FROM profiles WHERE locate(?, username)>0;
    <sql:param value="${param.search}"/>

</sql:query>

<div class="container">
<div class="col-md-4">
        <h3><span class="h3 text">Find friends</span></h3>
            <form id="search" class="input-group">
                <input type="search" name="search" class="form-control "  />
                <span class="input-group-btn">
                <button type="submit" class="btn btn-default"> Search</button>
                </span>
            </form>
</div>
</div>

<c:forEach var="row" items="${srh.rows}">
<div class="container">
        <li href="#" class="list-group-item text-left">
            <div class="media-body">
                <form method="get" action="/view">
                    <input type="hidden" name="view" value="${fn:escapeXml(row.id)}"/>
                    <button class="pull-left img-thumbnail img-circle" >
                    <img class="media-object img-circle" src="http://bootdey.com/img/Content/user_2.jpg" >
                    </button>
                </form>
                <div class="row">
                    <form method="get" action="/view">
                        <input type="hidden" name="view" value="${fn:escapeXml(row.id)}"/>
                        <div class="col-sm-4">
                        <h2 class="media-heading"><button class="btn-link " href="profile.jsp/view=${(row.username)}" >${fn:escapeXml(row.username)}</button></h2>
                          <p><i class="fa fa-map-marker"></i> &nbsp; ${fn:escapeXml(row.location)}
                           <br><small class="text-danger">&nbsp; ${fn:escapeXml(row.MF)} Mutual Friends</small></p>
                    </div>
                    </form>

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