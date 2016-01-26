<%--
  Created by IntelliJ IDEA.
  User: glenice
  Date: 16 Jan 2016
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.loggedIn}">
    </c:when>
    <c:otherwise>
        <% response.sendRedirect("/signin"); %>
    </c:otherwise>
</c:choose>
<html>
<jsp:include page="header.jsp">
    <jsp:param name="dashboard" value="active"></jsp:param>
</jsp:include>
<div class="container-fluid" style="margin-top: 60px;">
    <div class="row">
        <div class="btn-group btn-group-justified" role="group">
            <%--Post--%>
            <div class="col-md-4" style="text-align: center">
                <!--
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

                -->
                <h3 style="text-align: center">Diario</h3>
                <h4 style="color: grey; text-align: center;"><a href="/profile">${sessionScope.username}</a></h4>
                <input type="button" class="btn btn-default navbar-btn" onclick="location.href='/post';"
                       value="Post New Entry"/>
                <br/>
                <input type="button" class="btn btn-default navbar-btn" onclick="location.href='/viewPosts.jsp';"
                       value="View Diary"/>
                <br/>
                <input type="button" class="btn btn-default navbar-btn" onclick="location.href='/postImage.jsp';"
                       value="Post Image"/>
                <br/>
                <input type="button" class="btn btn-default navbar-btn" onclick="location.href='/viewImages.jsp';"
                       value="View Images"/>
                <br/>
                <input type="button" class="btn btn-default navbar-btn" onclick="location.href='/viewFriendsPosts.jsp';"
                       value="View Friends' Posts"/>
                <br/>
                <input type="button" class="btn btn-default navbar-btn" onclick="location.href='/viewFriendsImages.jsp';"
                       value="View Friends Images"/>
            </div>

            <%--Instagram--%>
            <div class="col-md-4">
                <h3 style="text-align: center;">Instagram</h3>
                <c:choose>
                    <c:when test="${sessionScope.instagram != null}">

                        <h4 style="color: grey; text-align: center;">${instagramUserInfo.getUsername()}</h4>

                        <%--<p style="color: grey; text-align: center;">
                            Bio: ${instagramUserInfo.getBio()}
                        </p>--%>

                        <br/>

                        <form action="/instagramSearch" method="post">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Text here" name="text">
                            <span class="input-group-btn">
                                <input class="btn btn-default" type="submit" name="post" value="Search"/>
                            </span>
                            </div>
                        </form>

                        <ul class="nav nav-tabs">
                            <li role="presentation" class="${sessionScope.InstagramUserTimelineActive}"><a
                                    href="/instagramUserTimeline">My
                                Profile</a></li>
                            <%--<li role="presentation" class="${sessionScope.InstagramHomeTimelineActive}"><a
                                    href="/instagramFeed">Timeline</a></li>

                            <li role="presentation" class="${sessionScope.InstagramSearch}"><a
                                    href="/instagramSearch">Search</a></li>--%>

                        </ul>

                        <c:choose>

                            <c:when test="${sessionScope.InstagramUserTimelineActive=='active'}">
                                <c:forEach var="media" items="${userFeed}">
                                    <div style="align: middle">
                                        <a class="thumbnail" href="${media.getLink()}">

                                            <div class="caption" style="align: middle">
                                                <img class="img-responsive"
                                                     src="${media.getImages().getLowResolution().getImageUrl()}"
                                                     alt="">
                                                <span class="glyphicon glyphicon-heart"></span>
                                                <c:out value="${media.getLikes().getCount()}"/>
                                                    ${media.getCaption().getText()}
                                                    ${media.getCreatedTime()}

                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>
                            </c:when>

                            <c:when test="${sessionScope.InstagramHomeTimelineActive=='active'}">
                                <c:forEach var="media" items="${instaHomeFeed}">
                                    <div style="align: middle">
                                        <a class="thumbnail" href="${media.getLink()}">

                                            <div class="caption" style="align: middle">
                                                <img class="img-responsive"
                                                     src="${media.getImages().getLowResolution().getImageUrl()}"
                                                     alt="">
                                                <span class="glyphicon glyphicon-heart"></span>
                                                <c:out value="${media.getLikes().getCount()}"/>

                                                    ${media.getCaption().getText()}
                                                    ${media.getCreatedTime()}

                                            </div>
                                        </a>
                                    </div>
                                </c:forEach>

                            </c:when>

                            <c:otherwise>
                                <c:forEach var="resultUser" items="${sessionScope.searchResults}">

                                    <b>${resultUser.getUserName()}</b>
                                    <br/>
                                    ${resultUser.getBio()}
                                    <br/>
                                    ${resultUser.getProfilePictureUrl()}
                                </c:forEach>
                            </c:otherwise>

                        </c:choose>


                    </c:when>

                    <c:otherwise>
                        <div style="text-align: center">

                            <a href="/instagramLogin">Login to Instagram</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <%--Twitter--%>
            <div class="col-md-4">
                <h3 style="color: 4099FF; font-family: Arial; text-align: center">Twitter</h3>
                <c:choose>
                    <c:when test="${sessionScope.twitter != null && sessionScope.requestToken == null}">
                        <h4 style="color: grey; text-align: center;">${twitter.screenName}</h4>

                        <br/>

                        <form action="/tweet" method="post">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Text here" name="text">
                            <span class="input-group-btn">
                                <input class="btn btn-default" type="submit" name="post" value="Tweet"/>
                            </span>
                            </div>

                        </form>

                        <!-- <a href="/twitterlogout">Logout</a> -->


                        <ul class="nav nav-tabs">
                            <li role="presentation" class="${sessionScope.userTimelineActive}"><a href="/userTimeline">My
                                Profile</a></li>
                            <li role="presentation" class="${sessionScope.homeTimelineActive}"><a
                                    href="/twitterTimeline">Timeline</a></li>
                                <%--<li role="presentation" class=""
                                "${sessionScope.messagesActive}><a href="#">Messages</a></li>--%>

                        </ul>

                        <%-- <nav>
                             <ul class="pagination" style="align: center">
                                 <li>
                                     <a href="#" aria-label="Previous">
                                         1
                                     </a>
                                 </li>
                                 <li><a href="#">2</a></li>
                                 <li><a href="#">3</a></li>
                                 <li><a href="#">4</a></li>
                                 <li>
                                     <a href="#" aria-label="Next">
                                         <span aria-hidden="true">5</span>
                                     </a>
                                 </li>
                             </ul>
                         </nav>

                         <br/>
 --%>

                        <br/>
                        <c:choose>

                            <c:when test="${sessionScope.homeTimelineActive == 'active'}">
                                <c:forEach var="status" items="${twitterTimelineList}">
                                    <div class="container-fluid">
                                        <img src="${status.getUser().getProfileImageURL()}" style="align: left"/>
                                        <b><c:out value="${status.getUser().getName()}"/></b><br/>
                                        <c:out value="${status.getText()}"/>

                                        <p style="text-align: center">
                                            <span class="glyphicon glyphicon-heart"></span>
                                            <c:out value="${status.getFavoriteCount()}"/>
                                            <span class="glyphicon glyphicon-retweet"></span>
                                            <c:out value="${status.getRetweetCount()}"/>
                                        </p>

                                    </div>
                                </c:forEach>
                            </c:when>

                            <c:when test="${sessionScope.userTimelineActive == 'active'}">
                                <c:forEach var="status" items="${userTimeline}">
                                    <div class="container-fluid">
                                        <img src="${status.getUser().getProfileImageURL()}" style="align: left"/>
                                        <b><c:out value="${status.getUser().getName()}"/></b><br/>
                                        <c:out value="${status.getText()}"/>
                                        <br/>
                                        <c:out value="${status.getCreatedAt()}"/>

                                        <p style="text-align: center">
                                            <span class="glyphicon glyphicon-heart"></span>
                                            <c:out value="${status.getFavoriteCount()}"/>
                                            <span class="glyphicon glyphicon-retweet"></span>
                                            <c:out value="${status.getRetweetCount()}"/>
                                        </p>
                                    </div>
                                </c:forEach>
                            </c:when>

                        </c:choose>

                    </c:when>


                    <c:otherwise>

                        <div style="text-align: center">
                            <a href="/twitterlogin" role="button">Login to Twitter</a>

                                <%--<a href="/twitterAccess">My Profile</a>--%>
                            <br/>
                        </div>

                    </c:otherwise>
                </c:choose>


            </div>
        </div>
    </div>
</div>
</html>
