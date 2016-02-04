<%--
  Created by IntelliJ IDEA.
  User: glenice
  Date: 25 Jan 2016
  Time: 12:54
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
    <jsp:param name="mood" value="active"></jsp:param>
</jsp:include>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

<script>

    $(function () {
        $('#container').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Mood chart'
            },
            xAxis: {
                categories: <%=request.getSession().getAttribute("dateList")%>
            },
            credits: {
                enabled: false
            },
            series: [{
                name: 'Positive',
                data: <%=request.getSession().getAttribute("positiveList")%>,
                color: Highcharts.getOptions().colors[3],
            },{
                name: 'Negative',
                data: <%=request.getSession().getAttribute("negativeList")%>,
                color: Highcharts.getOptions().colors[1],
            },{
                type: 'spline',
                name: 'Average',
                data: <%=request.getSession().getAttribute("compound")%>,
                color: Highcharts.getOptions().colors[0],

            }]
        });
    });
</script>

<div class="container-fluid" style="margin-top: 60px;">


    <div class="row">
        <div id="container" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>


    </div>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                        data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="true">
                    Choose Month
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li><a href="/chart?value=Jan">Jan</a></li>
                    <li><a href="/chart?value=Feb">Feb</a></li>
                    <li><a href="/chart?value=Mar">Mar</a></li>
                    <li><a href="/chart?value=Apr">Apr</a></li>
                    <li><a href="/chart?value=May">May</a></li>
                    <li><a href="/chart?value=Jun">Jun</a></li>
                    <li><a href="/chart?value=Jul">Jul</a></li>
                    <li><a href="/chart?value=Aug">Aug</a></li>
                    <li><a href="/chart?value=Sep">Sep</a></li>
                    <li><a href="/chart?value=Oct">Oct</a></li>
                    <li><a href="/chart?value=Nov">Nov</a></li>
                    <li><a href="/chart?value=Dec">Dec</a></li>
                    <li><a href="/chart?value=refresh">Refresh</a></li>
                </ul>
            </div>

            <br/>

            <c:choose>
                <c:when test="${sessionScope.listEmpty==true}">

                    <div class="panel panel-default">
                        <div class="panel-body">
                            No entry detected! Choose Another Month.
                        </div>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>

    <div class="row">
        <c:forEach var="content" items="${sessionScope.contentList}">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="thumbnail">
                        <img src="${content.getUrl()}" alt="">

                        <div class="caption">
                            <h3>${content.getFormatDate()}</h3>
                            <p>${content.getContent()}</p>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</div>

</html>