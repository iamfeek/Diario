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
    <jsp:param name="dashboard" value="active"></jsp:param>
</jsp:include>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

<script>
    $(function () {
        $('#container').highcharts({
            title: {
                text: 'Average Mood',
                x: -20 //center
            },
            subtitle: {
                text: 'Based on Diario',
                x: -20
            },
            xAxis: {
                categories: <%=request.getSession().getAttribute("dateList")%>
            },
            yAxis: {
                title: {
                    text: 'Percentage (%)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: '%'
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                /*name: 'Positive',
                data: <%=request.getSession().getAttribute("pos")%>
            }, {
                name: 'Negative',
                data: [-0.2, 0.8, 5.7, 11.3, 17.0]
            }, {
                name: 'Neutral',
                data: [-0.9, 0.6, 3.5, 8.4, 13.5]
            }, {*/
                name: 'Average',
                data: <%=request.getSession().getAttribute("compound")%>
            }]
        });
    });

</script>

<div class="container-fluid" style="margin-top: 60px;">
    <div class="row">
        <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>



    </div>
</div>

</html>