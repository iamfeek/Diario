<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Diario">

<jsp:attribute name="head_area">
</jsp:attribute>

<jsp:attribute name="body_area">
    <div style="padding: 30px; margin-left: 100px">
        <h1 style="font-size: 30px; padding-bottom: 20px">Your Images</h1>
        <jsp:include page="retrieveImages.jsp"></jsp:include>
    </div>
</jsp:attribute>
</t:layout>