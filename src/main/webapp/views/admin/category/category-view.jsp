<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css" />
<jsp:include page="/views/topbar/topbar.jsp" />

<div class="page">
  <div class="container">
    <div class="card" style="max-width:720px;margin:0 auto;">
      <h2>Chi tiết danh mục</h2>

      <div class="row" style="gap:24px; align-items:flex-start;">
        <div style="flex:1">
          <p><strong>Mã:</strong> ${item.cate_id}</p>
          <p><strong>Tên danh mục:</strong> <c:out value="${item.cate_name}"/></p>
          <p><strong>Mô tả:</strong> <c:out value="${item.description}"/></p>
        </div>
      </div>

      <div class="row" style="gap:12px; margin-top:16px;">
        <a class="btn" href="${pageContext.request.contextPath}/admin/category/edit?id=${item.cate_id}">Sửa</a>
        <a class="btn" href="${pageContext.request.contextPath}/admin/category/list">Quay lại danh sách</a>
      </div>
    </div>
  </div>
</div>
