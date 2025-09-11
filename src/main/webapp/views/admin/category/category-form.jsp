<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Sửa danh mục</title>
  <style>
    :root{
      --bg: #f9f7fb;
      --text: #4a235a;
      --primary: #d7bde2;
      --primary-hover: #bb8fce;
      --danger: #f5b7b1;
      --danger-hover: #ec7063;
      --muted: #6b7280;
      --shadow: 0 6px 16px rgba(74,35,90,0.08);
    }

    body {
      font-family: "Inter", Arial, sans-serif;
      background: var(--bg);
      color: var(--text);
      margin: 0; padding: 24px;
    }

    a {
      text-decoration: none;
      color: var(--primary);
    }
    a:hover { color: var(--primary-hover); }

    h2 { margin: 16px 0; }

    form {
      background: #fff;
      padding: 20px;
      border-radius: 12px;
      max-width: 400px;
      box-shadow: var(--shadow);
    }
    form div {
      margin-bottom: 14px;
    }
    label {
      display: block;
      margin-bottom: 6px;
      font-weight: 600;
    }
    input[type="text"] {
      width: 100%;
      padding: 8px 10px;
      border: 1px solid #ccc;
      border-radius: 6px;
      font-size: 14px;
    }
    input[type="text"]:focus {
      outline: none;
      border-color: var(--primary);
      box-shadow: 0 0 0 2px rgba(215,189,226,0.4);
    }

    .actions {
      display: flex;
      justify-content: center;
      gap: 12px;
      margin-top: 20px;
    }

    button, .btn-cancel {
      padding: 8px 18px;
      border-radius: 8px;
      border: none;
      cursor: pointer;
      font-weight: 600;
      min-width: 100px;
      text-align: center;
      transition: background-color .2s ease, transform .1s ease;
    }
    button {
      background: var(--primary);
      color: #fff;
    }
    button:hover { background: var(--primary-hover); }
    .btn-cancel {
      background: var(--danger);
      color: #fff;
      text-decoration: none;
      line-height: 32px;
      display: inline-block;
    }
    .btn-cancel:hover { background: var(--danger-hover); }
    button:active, .btn-cancel:active { transform: scale(0.97); }
  </style>
</head>
<body>

  <div><a href="${ctx}/admin/home">← Trang chủ</a></div>
  <h2>Sửa danh mục</h2>

  <form method="post" action="${ctx}/admin/category/update">
    <input type="hidden" name="id" value="${cate.cate_id}" />
    <div>
      <label for="name">Tên danh mục</label>
      <input type="text" id="name" name="name" value="${cate.cate_name}" required />
    </div>
    <div>
      <label for="desc">Mô tả</label>
      <input type="text" id="desc" name="description" value="${cate.description}" />
    </div>
    <div class="actions">
      <button type="submit">Cập nhật</button>
      <a href="${ctx}/admin/categories" class="btn-cancel">Hủy</a>
    </div>
  </form>

</body>
</html>
