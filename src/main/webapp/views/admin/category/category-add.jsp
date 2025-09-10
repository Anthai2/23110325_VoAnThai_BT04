<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h2>Thêm danh mục</h2>
<c:if test="${not empty alert}">
  <div style="color:red">${alert}</div>
</c:if>

<form method="post" action="${ctx}/admin/category/insert">
  <div>
    <label>Tên danh mục</label>
    <input type="text" name="name" required />
  </div>
  <div>
    <label>Mô tả</label>
    <input type="text" name="description" />
  </div>
  <button type="submit">Lưu</button>
  <a href="${ctx}/admin/categories">Hủy</a>
</form>
