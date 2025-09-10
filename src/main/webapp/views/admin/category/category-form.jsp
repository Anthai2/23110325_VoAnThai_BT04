<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<h2>Sửa danh mục</h2>
<form method="post" action="${ctx}/admin/category/update">
  <input type="hidden" name="id" value="${cate.cate_id}" />
  <div>
    <label>Tên danh mục</label>
    <input type="text" name="name" value="${cate.cate_name}" required />
  </div>
  <div>
    <label>Mô tả</label>
    <input type="text" name="description" value="${cate.description}" />
  </div>
  <button type="submit">Cập nhật</button>
  <a href="${ctx}/admin/categories">Hủy</a>
</form>
