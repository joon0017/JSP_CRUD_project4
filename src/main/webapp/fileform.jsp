<%--
  Created by IntelliJ IDEA.
  User: SeongJoon
  Date: 2022-11-18
  Time: 오후 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post"  action="fileupload.jsp" enctype="multipart/form-data">
        <input type="file" name="photo">
        <input type="submit" value="upload">
    </form>

</body>
</html>
