<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Danh mục</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f7fb; /* nền tím pastel nhạt */
        color: #4a235a;
        margin: 20px;
    }
    a, button {
        display: inline-block;
        padding: 6px 14px;
        margin: 2px;
        border-radius: 6px;
        text-decoration: none;
        font-size: 14px;
        text-align: center;
        transition: 0.3s;
    }
    a {
        background-color: #d7bde2; /* tím pastel */
        color: #fff;
        border: none;
    }
    a:hover {
        background-color: #bb8fce;
    }
    button {
        background-color: #f5b7b1; /* hồng pastel */
        color: #fff;
        border: none;
        cursor: pointer;
    }
    button:hover {
        background-color: #ec7063;
    }
    .nav-links {
        margin-bottom: 16px;
    }
    .nav-links a {
        margin-right: 10px;
    }
    table {
        border-collapse: collapse;
        width: 100%;
        background: #fff;
        border-radius: 8px;
        overflow: hidden;
        box-shadow: 0 2px 6px rgba(0,0,0,0.1);
    }
    th, td {
        padding: 10px;
        text-align: center;
        border-bottom: 1px solid #ddd;
    }
    th {
        background-color: #d2b4de;
        color: #4a235a;
    }
    tr:hover {
        background-color: #f4ecf7;
    }
    .action-buttons {
        display: flex;
        justify-content: center;
        gap: 10px;
    }
</style>
</head>
<body>

<div class="nav-links">
  <a href="${ctx}/admin/home">Trang chủ</a>
  <a href="${ctx}/admin/category/add">Add Category</a>
</div>

<table>
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
        <div class="action-buttons">
          <a href="${ctx}/admin/category/edit?id=${c.cate_id}">Sửa</a>
          <form action="${ctx}/admin/category/delete" method="post"
                onsubmit="return confirm('Xoá danh mục này?');">
            <input type="hidden" name="id" value="${c.cate_id}">
            <button type="submit">Xóa</button>
          </form>
        </div>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
