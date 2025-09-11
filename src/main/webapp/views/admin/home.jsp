<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Admin Home</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style>
    :root{
      --bg: #faf7fd;
      --bg-grad: linear-gradient(180deg,#faf7fd, #f4eefb);
      --text: #2f1140;
      --muted:#6e6a78;
      --card:#ffffff;
      --border:#ece8f2;
      --primary:#cda9e6;
      --primary-strong:#b88ad9;
      --outline:#efe9fb;
      --shadow: 0 10px 24px rgba(95,41,145,.08);
      --shadow-hover: 0 14px 30px rgba(95,41,145,.14);
      --radius:16px;
    }

    *{box-sizing:border-box}
    html,body{height:100%}
    body{
      margin:0;
      font-family: Inter, system-ui, -apple-system, Segoe UI, Arial, sans-serif;
      color:var(--text);
      background: var(--bg-grad);
    }

    .container{
      max-width: 1100px;
      margin: 0 auto;
      padding: 28px 20px 40px;
    }

    /* Header */
    .topbar{
      display:flex; align-items:center; justify-content:space-between;
      margin-bottom: 28px;
    }
    .welcome{font-weight:700; font-size:18px}
    .pill{
      border:none; background:#e9def7; color:#3a1b52;
      border-radius:999px; padding:10px 18px; font-weight:600;
      cursor:pointer; transition:.2s transform ease,.2s background-color ease;
    }
    .pill:hover{ background:#decef6; transform:translateY(-1px) }
    .pill:active{ transform:translateY(0) }

    h2{ margin:8px 0 8px; font-size:28px }
    .sub{ color:var(--muted); margin-bottom:22px }

    /* Grid cards */
    .grid{
      display:grid; gap:20px;
      grid-template-columns: repeat(12,minmax(0,1fr));
    }
    .card{
      grid-column: span 6 / span 6;
      background:var(--card); border:1px solid var(--border);
      border-radius:var(--radius); padding:20px;
      box-shadow:var(--shadow);
      display:flex; flex-direction:column; min-height:180px;
      transition:.2s box-shadow ease, .2s transform ease;
    }
    .card:hover{ box-shadow:var(--shadow-hover); transform: translateY(-2px) }
    @media (max-width: 768px){
      .card{ grid-column: 1 / -1; }
    }

    .card h3{ margin:0 0 6px; font-size:20px }
    .muted{ color:var(--muted); margin:0 0 16px }

    /* Actions (nút cố định dưới – căn trái) */
    .actions{
      margin-top:auto;
      display:flex; gap:12px; align-items:center;
    }
    .btn{
      display:inline-flex; align-items:center; justify-content:center;
      min-width: 180px; height:40px; padding:0 16px;
      border-radius:10px; font-weight:700; text-decoration:none;
      border:1px solid transparent; cursor:pointer;
      transition:.2s background-color ease, .1s transform ease;
    }
    .btn:active{ transform:scale(.985) }
    .btn-primary{ background:var(--primary); color:#fff; border-color:var(--primary) }
    .btn-primary:hover{ background:var(--primary-strong) }
    .btn-outline{ background:var(--outline); color:var(--text); border-color:var(--outline) }
    .btn-outline:hover{ background:#e6dbf7 }
  </style>
</head>
<body>
  <div class="container">
    <div class="topbar">
      <div class="welcome">
        Xin chào,
        <c:choose>
          <c:when test="${not empty sessionScope.account}">
            ${sessionScope.account.username}
          </c:when>
          <c:otherwise>Admin</c:otherwise>
        </c:choose>
      </div>
      <form method="post" action="${pageContext.request.contextPath}/logout">
        <button type="submit" class="pill">Đăng xuất</button>
      </form>
    </div>

    <h2>Bảng điều khiển</h2>
    <p class="sub">Chọn một thao tác nhanh bên dưới.</p>

    <div class="grid">
      <!-- Card: Category List -->
      <div class="card">
        <h3>Category List</h3>
        <p class="muted">Xem danh sách danh mục hiện có, sửa hoặc xoá.</p>
        <div class="actions">
          <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/categories">Mở danh sách</a>
        </div>
      </div>

      <!-- Card: Add Category -->
      <div class="card">
        <h3>Add Category</h3>
        <p class="muted">Tạo danh mục mới bằng biểu mẫu nhập liệu.</p>
        <div class="actions">
          <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/category/add">Tạo mới</a>
          <!-- hoặc thêm 1 nút phụ -->
          <!-- <a class="btn btn-outline" href="#">Hướng dẫn</a> -->
        </div>
      </div>
    </div>
  </div>
</body>
</html>
