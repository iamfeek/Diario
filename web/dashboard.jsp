<%--
  Created by IntelliJ IDEA.
  User: glenice
  Date: 28 Nov 2015
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:main title="Diario">

<jsp:attribute name="head_area">
    <%--all custom js/css for this page belongs here.--%>
</jsp:attribute>

    <jsp:attribute name="body_area">
        <c:choose>
            <c:when test="${sessionScope.twitter != null && sessionScope.requestToken == null}">
                <h3>Twitter Logged In As: ${twitter.screenName}</h3>

                <a href="/twitterlogout">Logout</a>
                <br/>
                <form action="/tweet" method="post">
                    Update tweets:<br/>
                    <textarea cols="20" rows="2" name="text"></textarea>
                    <br/>
                    <input type="submit" name="post" value="update"/>
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
                <a href="/twitterlogin">Login to Twitter</a>
            </c:otherwise>
        </c:choose>

        <br/><br/>

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
                <a href="/facebookLogin">Login to Facebook</a>
            </c:otherwise>

        </c:choose>

        <br/><br/>

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

                    </a>
                    <br/><br/>

                </c:forEach>

            </c:when>

            <c:otherwise>
                <a href="/instagramLogin">Login to Instagram</a>
            </c:otherwise>


        </c:choose>

    </jsp:attribute>
</t:main>