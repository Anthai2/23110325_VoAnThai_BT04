<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Admin Home</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Optional Bootstrap (nếu bạn đã copy vào /assets/bootstrap/) -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/bootstrap.min.css">
  <style>
    body { padding: 24px; }
    .grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(260px, 1fr)); gap: 16px; }
    .card { border: 1px solid #e5e7eb; border-radius: 12px; padding: 18px; }
    .card h3 { margin: 0 0 6px; font-size: 18px; }
    .muted { color: #6b7280; font-size: 14px; margin-bottom: 12px; }
    .btn { display: inline-block; padding: 8px 14px; border-radius: 8px; text-decoration: none; border: 1px solid #e5e7eb; }
    .btn-primary { background: #0d6efd; color: #fff; border-color: #0d6efd; }
    .btn-outline { background: #fff; color: #111827; }
    header { display:flex; align-items:center; justify-content:space-between; margin-bottom: 24px; }
    header .welcome { font-weight: 600; }
  </style>
</head>
<body>

<header>
  <div class="welcome">
    Xin chào,
    <c:choose>
      <c:when test="${not empty sessionScope.account}">
        ${sessionScope.account.username}
      </c:when>
      <c:otherwise>Admin</c:otherwise>
    </c:choose>
  </div>

  <!-- Logout (tùy bạn dùng GET hay POST) -->
  <form method="post" action="${pageContext.request.contextPath}/logout" style="margin:0">
    <button class="btn btn-outline" type="submit">Đăng xuất</button>
  </form>
</header>

<h2 style="margin-bottom:12px">Bảng điều khiển</h2>
<p class="muted">Chọn một thao tác nhanh bên dưới.</p>

<div class="grid">
  <!-- Danh sách Category -->
  <div class="card">
    <h3>Category List</h3>
    <p class="muted">Xem danh sách danh mục hiện có, sửa hoặc xoá.</p>
    <a class="btn btn-primary"
       href="${pageContext.request.contextPath}/admin/categories">Mở danh sách</a>
  </div>

  <!-- Thêm Category -->
  <div class="card">
    <h3>Add Category</h3>
    <p class="muted">Tạo danh mục mới bằng biểu mẫu nhập liệu.</p>
    <a class="btn btn-primary"
       href="${pageContext.request.contextPath}/admin/category/add">Tạo mới</a>
  </div>

  <!-- Mẫu Form (nếu bạn muốn mở trực tiếp trang form dùng chung) -->
  <div class="card">
    <h3>Category Form</h3>
    <p class="muted">Mở trang form (phiên bản add) để thử nhập dữ liệu.</p>
    <a class="btn btn-outline"
       href="${pageContext.request.contextPath}/admin/category/add">Mở form</a>
  </div>
</div>

<!-- Optional Bootstrap JS -->
<script src="${pageContext.request.contextPath}/assets/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
