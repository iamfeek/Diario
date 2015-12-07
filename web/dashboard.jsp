<%--
  Created by IntelliJ IDEA.
  User: glenice
  Date: 28 Nov 2015
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:layout title="Diario">

<jsp:attribute name="head_area">
    <%--all custom js/css for this page belongs here.--%>
</jsp:attribute>

    <jsp:attribute name="body_area">
        <c:choose>
            <c:when test="${sessionScope.twitter != null && sessionScope.requestToken == null}">
                <h3>Welcome ${twitter.screenName}</h3>

                <a href="/twitterlogout">Logout</a>
                <br/>
                <form action="/tweet" method="post">
                    Update tweets:<br/>
                    <textarea cols="20" rows="2" name="text"></textarea>
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
                <a href="/twitterlogin">Twitter login</a>
            </c:otherwise>
        </c:choose>

        <br/><br/>

        <c:choose>
            <c:when test="${sessionScope.facebook != null}">
                <a href="/facebookfeed">Get Facebook Timeline</a>

                <c:forEach var="news" items="${fbTimeline}">
                    <b><c:out value="${news.getListType()}"/></b>

                    <c:out value="${news.getId()}"/>
                    <br/><br/>
                    <a href="/dashboard">hello</a>
                </c:forEach>
            </c:when>

        </c:choose>

    </jsp:attribute>
</t:layout>