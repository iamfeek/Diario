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
<script type="text/javascript" src="js/security/rsa-bundle.js"></script>
<script type="text/javascript" src="js/security/key-handler.js"></script>
<script type="text/javascript" src="js/security/aes-handler.js"></script>
<script type="text/javascript" src="js/security/aes.js"></script>
<script type="text/javascript" src="js/security/mode-ecb-min.js"></script>
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

                <div>
                    <div>
                        <%@ page import="DAO.DAOPost" %>
                        <%@ page import="java.util.ArrayList" %>
                        <%@ page import="post.Post" %>
                        <%@ page import="java.text.DateFormat" %>
                        <%@ page import="java.util.Collections" %>
                        <%
                            ArrayList<Post> posts = DAOPost.getPosts(session.getAttribute("username").toString());
                            Collections.sort(posts);
                            if (posts.size() == 0) {
                                out.println("<div style=\"padding: 20px; padding-bottom: 10px\">You do not have any posts</div>");
                            }
                            for (int i = 0; i < posts.size(); i++) {
                                String post = "<div style=\"padding: 20px; padding-bottom: 10px\">";
                                post += DateFormat.getDateTimeInstance().format(posts.get(i).getDate());
                                post += "</div>";
                                post += "<div style=\"padding: 20px; padding-top: 0px; border-bottom: 1px solid #CCCCCC\"";
                                if (posts.get(i).getEncrypted() && posts.get(i).getShared())
                                    post += " class=\"RSA_AES\"";
                                else if (posts.get(i).getEncrypted())
                                    post += " class=\"RSA\"";
                                post += '>';
                                post += posts.get(i).getText();
                                if (posts.get(i).getEncrypted() && posts.get(i).getShared()) {
                                    post += ";AES:";
                                    post += DAOPost.getAESKey(posts.get(i).getPostid(), session.getAttribute("username").toString());
                                }
                                post += "</div>";
                                out.println(post);
                            }
                        %>
                        <script type="text/javascript">
                            //decrypt all RSA posts
                            var rsaPosts = document.getElementsByClassName("RSA");
                            for (var i = 0; i < rsaPosts.length; i++) {
                                rsaPosts[i].innerText = decryptString(rsaPosts[i].innerText);
                            }

                            //decrypt all RSAxAES Posts
                            var rsaAesPosts = document.getElementsByClassName("RSA_AES");
                            for (var i = 0; i < rsaAesPosts.length; i++) {
                                var fulltext = rsaAesPosts[i].innerText;
                                var splittext = fulltext.split(";AES:");
                                var AESKey = decryptString(splittext[1]);
                                rsaAesPosts[i].innerText = decryptAES(splittext[0], AESKey);
                            }
                        </script>
                    </div>
                </div>
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

                                    <div class="thumbnail">
                                        <a href="${media.getLink()}">
                                            <img class="img-responsive"
                                                 src="${media.getImages().getLowResolution().getImageUrl()}"
                                                 alt="">

                                            <div class="caption" style="align: middle">

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
                                    <div class="media">
                                        <div class="media-left media-middle">
                                            <img src="${status.getUser().getProfileImageURL()}" class="media-object"/>


                                        </div>
                                        <div class="media-body">

                                            <h4 class="media-heading"><c:out
                                                    value="${status.getUser().getName()}"/></h4>

                                            <c:out value="${status.getText()}"/>

                                            <p style="text-align: center">
                                                <span class="glyphicon glyphicon-heart"></span>
                                                <c:out value="${status.getFavoriteCount()}"/>
                                                <span class="glyphicon glyphicon-retweet"></span>
                                                <c:out value="${status.getRetweetCount()}"/>
                                            </p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:when>

                            <c:when test="${sessionScope.userTimelineActive == 'active'}">
                                <c:forEach var="status" items="${userTimeline}">
                                    <div class="media">
                                        <div class="media-left media-middle">
                                            <img src="${status.getUser().getProfileImageURL()}" class="media-object"/>


                                        </div>
                                        <div class="media-body">

                                            <h4 class="media-heading"><c:out
                                                    value="${status.getUser().getName()}"/></h4>

                                            <c:out value="${status.getText()}"/>

                                            <p style="text-align: center">
                                                <span class="glyphicon glyphicon-heart"></span>
                                                <c:out value="${status.getFavoriteCount()}"/>
                                                <span class="glyphicon glyphicon-retweet"></span>
                                                <c:out value="${status.getRetweetCount()}"/>
                                            </p>
                                        </div>
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
