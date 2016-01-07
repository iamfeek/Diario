<%@ page import="friends.FriendsBean" %>
<%@ page import="DAO.Friend" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <link href="../css/profile.css" rel="stylesheet">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
</head>
<body>
<div id="snippetContent" style="padding-top:10px;">
    <div class="container">
        <div class="header">
            <h3 class="text-muted prj-name"><span class="fa fa-users fa-2x principal-title"></span> Friend zone</h3>
        </div>
        <form method="get" >
        <div class="jumbotron list-content">
            <ul class="list-group">
                <li href="#" class="list-group-item title"> Your friend zone</li>

                <form method="get" action="/search">
                <input type="search"  id="search" title="search" />
                <button type="search" name="search" > Search</button>
                </form>

                <li href="#" class="list-group-item text-left">
                    <img class="media-object img-circle" src="http://bootdey.com/img/Content/user_1.jpg">
                    <label class="name"> NH Akemi </label>
                    <label class="pull-right">
                        <a class="btn btn-success btn-xs glyphicon glyphicon-ok" href="#" title="View"></a>
                        <a class="btn btn-danger  btn-xs glyphicon glyphicon-trash" href="#" title="Delete"></a>
                        <a class="btn btn-info  btn-xs glyphicon glyphicon glyphicon-comment" href="#" title="Send message"></a>
                    </label>
                    <div class="break"></div>
                </li>
                <%
                    FriendsBean fb = new FriendsBean();
                    List<Friend> list = fb.getFriends();
                    for (Friend f : list) {
                %>
                <li href="#" class="list-group-item text-left">
                    <div class="media-body">
                        <a class="pull-left" href="#fakelink">
                            <img class="media-object img-circle" src="http://bootdey.com/img/Content/user_2.jpg">
                        </a>

                        <div class="row">
                            <div class="col-sm-4">
                                <h2 class="media-heading"><a href="#fakelink" ><%=f.getfUNAME()%></a></h2>
                                <p><i class="fa fa-map-marker"></i><%=f.getLocation()%>
                                    <br><small class="text-danger"><%=f.getMF()%></small></p>
                            </div>

                            <div style="float: right; padding-right: 15px;">
                                <form method="post" action="/view" >
                                    <input type="hidden" id="view" name="view" value="<%=f.getfUNAME()%>"/>
                                    <a type="submit" class="btn btn-success btn-xs glyphicon glyphicon-ok" href="profile.jsp"> View</a>
                                </form>
                                <form method="post" action="/unfriend">
                                    <input type="hidden" id="unfriend" name="unfriend" value="<%=String.valueOf(f.getId())%>"/>
                                    <a type="submit" class="btn btn-danger  btn-xs glyphicon glyphicon-trash"> Unfriend</a>
                                </form>
                                <form method="post" action="/message">
                                    <input type="hidden" id="message" name="message" value="<%=String.valueOf(f.getId())%>"/>
                                    <a type="submit" class="btn btn-info  btn-xs glyphicon glyphicon glyphicon-comment"> Message</a>
                                </form>
                            </div>

                        </div>
                        <%
                            }
                        %>


                    </div><!-- /.row -->
                    <div class="break"></div>
                </li>
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