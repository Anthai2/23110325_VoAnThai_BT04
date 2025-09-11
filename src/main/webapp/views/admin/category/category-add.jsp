<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Thêm danh mục</title>
<style>
:root {
	--bg: #faf7fd;
	--text: #2f1140;
	--primary: #cda9e6;
	--primary-hover: #b88ad9;
	--danger: #f5b7b1;
	--danger-hover: #ec7063;
	--shadow: 0 8px 18px rgba(95, 41, 145, 0.08);
}

body {
	font-family: "Inter", Arial, sans-serif;
	background: var(--bg);
	color: var(--text);
	margin: 0;
	padding: 40px;
	display: flex;
	justify-content: center;
}

h2 {
	margin: 16px 0;
}

.form-box {
	background: #fff;
	padding: 28px 30px;
	border-radius: 14px;
	box-shadow: var(--shadow);
	max-width: 440px;
	width: 100%;
}

form div {
	margin-bottom: 16px;
}

label {
	display: block;
	margin-bottom: 6px;
	font-weight: 600;
}

input[type="text"] {
	width: calc(100% - 10px); /* nhỏ hơn panel một chút */
	padding: 8px 12px;
	border: 1px solid #ccc;
	border-radius: 8px;
	font-size: 14px;
}

input[type="text"]:focus {
	outline: none;
	border-color: var(--primary);
	box-shadow: 0 0 0 2px rgba(205, 169, 230, 0.4);
}

.actions {
	display: flex;
	justify-content: center;
	gap: 16px;
	margin-top: 20px;
}

.btn {
	display: inline-flex; /* Căn giữa dọc & ngang cho cả <a> và <button> */
	align-items: center;
	justify-content: center;
	width: 160px; /* 2 nút bằng chiều rộng */
	height: 44px; /* và bằng chiều cao */
	border-radius: 10px;
	font-weight: 700;
	font-size: 14px;
	border: 0;
	cursor: pointer;
	text-decoration: none; /* bỏ gạch chân ở <a> */
	transition: background-color .2s ease, transform .1s ease;
}

.btn:active {
	transform: scale(.98);
}

.btn-primary {
	background: var(--primary);
	color: #fff;
}

.btn-primary:hover {
	background: var(--primary-hover);
}

.btn-cancel {
	background: var(--danger);
	color: #fff;
}

.btn-cancel:hover {
	background: var(--danger-hover);
}
</style>

</head>
<body>

	<div class="form-box">
		<h2>Thêm danh mục</h2>
		<form method="post" action="${ctx}/admin/category/insert">
			<div>
				<label for="name">Tên danh mục</label> <input type="text" id="name"
					name="name" required />
			</div>
			<div>
				<label for="desc">Mô tả</label> <input type="text" id="desc"
					name="description" />
			</div>
			<div class="actions">
				<button type="submit" class="btn btn-primary">Lưu</button>
				<a href="${ctx}/admin/categories" class="btn btn-cancel">Hủy</a>
			</div>
		</form>
	</div>

</body>
</html>
