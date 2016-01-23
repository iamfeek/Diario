<%--
  Created by IntelliJ IDEA.
  User: glenice
  Date: 16 Jan 2016
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Diario</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
          integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>
</head>
<body>

<div class="row">

    <div class="btn-group btn-group-justified" role="group">
        <div class="col-md-4">
            <c:choose>
                <c:when test="${sessionScope.twitter != null && sessionScope.requestToken == null}">
                    <h3>Twitter Username: ${twitter.screenName}</h3>

                    <a href="/twitterlogout">Logout</a>
                    <br/>

                    <form action="/tweet" method="post">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Text here" name="text">
                            <span class="input-group-btn">
                                <input class="btn btn-default" type="submit" name="post" value="update">Tweet!</input>
                            </span>
                        </div>

                    </form>

                    <a href="/twitterTimeline">Get Timeline</a>
                    <br/>
                    <c:forEach var="status" items="${twitterTimelineList}">
                        <b><c:out value="${status.getUser().getName()}"/></b>
                        <c:out value="${status.getText()}"/>
                        <br/><br/>
                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <a href="/twitterlogin" role="button">Login to Twitter</a>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="col-md-4">
            <c:choose>
                <c:when test="${sessionScope.instagram != null}">
                    <h3>Instagram Logged In As: ${instagramUserInfo.getUsername()}</h3>

                    <p>
                        Bio: ${instagramUserInfo.getBio()}
                    </p>

                    <c:forEach var="media" items="${userFeed}">
                        <a class="thumbnail" href="#">
                            <img class="img-responsive" src="${media.getImages().getLowResolution().getImageUrl()}"
                                 alt="">

                            <p>${media.getCaption().getText()}</p>

                            <p>${media.getCaption().getCreatedTime()}</p>
                        </a>
                        <br/><br/>

                    </c:forEach>

                </c:when>

                <c:otherwise>
                    <a href="/instagramLogin">Login to Instagram</a>
                </c:otherwise>
            </c:choose>
        </div>


        <div class="col-md-4">

            <c:choose>
                <c:when test="${sessionScope.facebook != null}">
                    <h3> Facebook Logged In As : ${facebook.getName()}</h3>

                    <a href="/facebookfeed">Get Facebook Timeline</a>

                    ${token}
                    <c:forEach var="news" items="${fbFriendList}">
                        <b><c:out value="${news.getName()}"/></b>

                        <c:out value="${news.getId()}"/>
                        <br/><br/>
                        <a href="/dashboard">hello</a>
                    </c:forEach>
                </c:when>

                <c:otherwise>
                    <a href="/facebookLogin" role="button">Login to Facebook</a>
                </c:otherwise>

            </c:choose>
        </div>
    </div>
</div>

</body>
</html>
