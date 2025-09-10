<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
</head>
<body>
<%@ include file="../topbar/topbar.jsp" %>

<div class="container">
    <div class="page">
        <h2>Xin chào, ${sessionScope.account.fullname}!</h2>
        <p>Bạn đã đăng nhập thành công. Đây là trang dành cho người dùng.</p>
    </div>
</div>
</body>
</html>