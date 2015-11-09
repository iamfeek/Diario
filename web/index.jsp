<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Diario">

<jsp:attribute name="head_area">
    <%--all custom js/css for this page belongs here.--%>
</jsp:attribute>

<jsp:attribute name="body_area">
    <%--the content of the site goes here.--%>
        <h1>Welcome</h1>
    <script>
        document.getElementById('homeNav').className = 'active';
    </script>
</jsp:attribute>
</t:layout>