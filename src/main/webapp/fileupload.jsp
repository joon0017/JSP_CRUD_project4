<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.File" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.crud.common.FileUpload" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 결과</title>
</head>

<body>
    <%
    String filename= "";
    int sizeLimit = 15 * 1024 * 1024; // 15MB

    //저장 경로
    String realPath = request.getSession().getServletContext().getRealPath("upload");

    //저장 경로 없을 시 생성
    File dir = new File(realPath);
    if(!dir.exists()) {
        dir.mkdirs();
    }

    //업로드 처리 과정

        MultipartRequest multipartRequest = new MultipartRequest(request, realPath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
        filename= multipartRequest.getFilesystemName("photo");
    %>

    <h1>파일 업로드 결과</h1>
    <p>폼에서 전송된 원래 파일명 : <%=multipartRequest.getOriginalFileName("photo")%> <br>
    업로드한 파일의 경로 : ${pageContext.request.contextPath}/upload/<%=filename%><br>
    물리적인 저장 경로 : <%=realPath%><br>
    <img src="${pageContext.request.contextPath}/upload/<%=filename%>"><br>

    파일명 : <%=filename%> <br>
    </p>
</body>
</html>