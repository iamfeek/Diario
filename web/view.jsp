<%--@elvariable id="nono" type="DAO.Friend"--%>
<html>
<head>
    <title>Hello</title>
</head>
<body>

<div class="container">
    <h3> ${nono.username} </h3>
    <p> Location: ${nono.location} <br>
        ${nono.MF} Mutual Friends </p>
    <p>
        ${nono.username} has created ${nono.posts} posts.<br>
        ${nono.username} made ${nono.friends} friends.
    </p>
</div>

</body>
</html>