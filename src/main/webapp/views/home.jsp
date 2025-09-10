<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manager home</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
<jsp:include page="./topbar/topbar.jsp"/>
<div class="page">
    <h2>Xin chào Manager: ${sessionScope.account.fullname}</h2>
    <p>Đây là trang quản lý dành cho manager.</p>
</div>
</body>
</html>