<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Diario</title>
    <script src="js/jquery.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">

    <link rel="stylesheet" href="css/bootstrap-theme.css">

    <script src="js/bootstrap.js"></script>
    <link rel="stylesheet" href="css/font-awesome.css">
    <style>
        .dropdown:hover .dropdown-menu {
            display: block;
        }
    </style>

</head>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/dashboard">Diario</a>
        </div>
        <ul class="nav navbar-nav navbar-left">
            <li class="${param.dashboard}"><a href="/dashboard">Dashboard</a></li>
            <li class="dropdown ${param.post}" href="post.jsp">
                <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false">Posts <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li class="dropdown-header">Add New Content</li>
                    <li class="${param.postNew}"><a href="post.jsp">New Post</a></li>
                    <li class="${param.postImage}"><a href="postImage.jsp">New Image</a></li>
                    <li role="separator" class="divider"></li>
                    <li class="dropdown-header">View Your Content</li>
                    <li class="${param.postView}"><a href="viewPosts.jsp">View My Posts</a></li>
                    <li class="${param.postViewImages}"><a href="viewImages.jsp">View My Images</a></li>
                    <li role="separator" class="divider"></li>
                    <li class="dropdown-header">View Your Friends' Content</li>
                    <li class="${param.postViewFriendPosts}"><a href="viewFriendsPosts.jsp">View Their Posts</a></li>
                    <li class="${param.postViewFriendImages}"><a href="viewFriendsImages.jsp">View Their Images</a></li>
                </ul>
            </li>
            <li class="${param.mood}"><a href="/mood.jsp">How was my mood?</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right" style="margin-right: 5px;">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                   aria-expanded="false">Hello, ${sessionScope.username} <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li class="dropdown-header">Profile Settings</li>
                    <li class="${param.profile}"><a href="/view?view=${sessionScope.userid}">View Profile</a></li>
                    <li role="separator" class="divider"></li>
                    <li class="dropdown-header">Please.. Don't....</li>
                    <li id="signoutLink"><a href="javascript:void(0);">Sign Out</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <script>
        $(document).ready(function () {
            $("#signoutLink a").click(function (){
                if(confirm("You are about to log out. Click ok to confirm")){
                    window.location.href="/signout";
                }
            })
        });
    </script>
</nav>
