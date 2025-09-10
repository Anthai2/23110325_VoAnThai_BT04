<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<p><a href="${ctx}/admin/category/add">Add Category</a></p>

<table border="1" cellpadding="6" cellspacing="0" width="100%">
  <thead>
    <tr>
      <th>STT</th>
      <th>CategoryID</th>
      <th>CategoryName</th>
      <th>Description</th>
      <th>Action</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${listcate}" var="c" varStatus="st">
    <tr>
      <td>${st.index + 1}</td>
      <td>${c.cate_id}</td>
      <td>${c.cate_name}</td>
      <td>${c.description}</td>
      <td>
        <a href="${ctx}/admin/category/edit?id=${c.cate_id}">Sửa</a>
        &nbsp;|&nbsp;
        <form action="${ctx}/admin/category/delete" method="post" style="display:inline"
              onsubmit="return confirm('Xoá danh mục này?');">
          <input type="hidden" name="id" value="${c.cate_id}">
          <button type="submit" style="background:none;border:none;color:#00f;cursor:pointer;padding:0">Xóa</button>
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
