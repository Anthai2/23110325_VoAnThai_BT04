<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8" />
<title>Đăng nhập</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" />
<style>
:root {
	--bg: #f9f7fb; /* nền tím nhạt */
	--panel: #ffffff; /* panel sáng */
	--text: #2d1b3d; /* tím đậm */
	--muted: #7a6c84;
	--primary: #b086d9; /* tím pastel */
	--primary-2: #965fca; /* hover tím đậm hơn */
	--ring: #e0d0f2; /* viền focus */
	--success-bg: #e8f6ec;
	--success-text: #1e7e34;
	--danger-text: #b82e2e;
	--shadow: 0 12px 30px rgba(80, 50, 120, .15);
	--radius: 14px;
}

* {
	box-sizing: border-box
}

html, body {
	height: 100%
}

body {
	margin: 0;
	background: var(--bg);
	color: var(--text);
	font-family: Inter, system-ui, -apple-system, Segoe UI, Arial,
		sans-serif;
	display: flex;
	align-items: center;
	justify-content: center;
	padding: 24px;
}

.login-container {
	width: 100%;
	max-width: 520px;
	background: var(--panel);
	border-radius: var(--radius);
	box-shadow: var(--shadow);
	padding: 28px 26px 24px;
}

h1 {
	margin: 0 0 6px;
	font-size: 30px;
	font-weight: 800;
	letter-spacing: .2px;
}

.welcome {
	color: var(--muted);
	margin-bottom: 14px
}

/* Alert */
.alert {
	border-radius: 10px;
	padding: 10px 12px;
	background: var(--success-bg);
	color: var(--success-text);
	border: 1px solid #cfe9d6;
	margin-bottom: 14px;
	font-size: 14px;
}

.alert-error {
	background: #fdecec;
	color: var(--danger-text);
	border-color: #f5c2c2;
}

/* Input group */
.input-group {
	position: relative;
	margin-bottom: 14px;
}

.input-group .icon {
	position: absolute;
	left: 12px;
	top: 50%;
	transform: translateY(-50%);
	color: #bda890;
	font-size: 14px;
}

.input {
	width: 100%;
	height: 46px;
	padding: 10px 12px 10px 38px;
	border: 1.5px solid #eadfce;
	border-radius: 12px;
	background: #fff;
	color: var(--text);
	font-size: 15px;
	outline: none;
	transition: border-color .2s ease, box-shadow .2s ease;
}

.input:focus {
	border-color: var(--primary-2);
	box-shadow: 0 0 0 3px var(--ring);
}

label {
	display: block;
	font-weight: 700;
	margin-bottom: 6px;
}

/* Options row */
.options {
	display: flex;
	align-items: center;
	justify-content: space-between;
	margin: 6px 0 14px;
	font-size: 14px;
	color: var(--muted);
}

.options a {
	color: var(--primary-2);
	text-decoration: none
}

.options a:hover {
	text-decoration: underline
}

/* Button */
.btn {
	width: 180px;
	height: 44px;
	border: none;
	border-radius: 24px;
	background: var(--primary);
	color: #fff;
	font-weight: 800;
	letter-spacing: .4px;
	display: block;
	margin: 10px auto 0;
	cursor: pointer;
	transition: transform .08s ease, background-color .2s ease, box-shadow
		.2s ease;
	box-shadow: 0 6px 12px rgba(139, 94, 52, .22);
}

.btn:hover {
	background: var(--primary-2)
}

.btn:active {
	transform: translateY(1px)
}

/* Footer links */
.register-text {
	text-align: center;
	color: var(--muted);
	margin-top: 14px;
}

.register-text a {
	color: var(--primary-2);
	text-decoration: none
}

.register-text a:hover {
	text-decoration: underline
}

/* Small helper text */
.hint {
	color: var(--muted);
	font-size: 14px;
	margin-bottom: 8px
}
</style>
</head>
<body>

	<div class="login-container">
		<h1>Đăng nhập</h1>
		<div class="welcome">
			Chào mừng trở lại <span aria-hidden="true">👋</span>
		</div>

		<!-- Alert: ưu tiên hiển thị alert lỗi; nếu bạn muốn hiển thị thông báo thành công khác, đổi class -->
		<c:if test="${not empty alert}">
			<div class="alert alert-error">${alert}</div>
		</c:if>
		<c:if test="${not empty success}">
			<div class="alert">${success}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/login" method="post"
			autocomplete="on">
			<label for="username">Tài khoản</label>
			<div class="input-group">
				<i class="fa fa-user icon"></i> <input id="username" name="username"
					type="text" class="input" placeholder="Nhập tài khoản" required />
			</div>

			<label for="password">Mật khẩu</label>
			<div class="input-group">
				<i class="fa fa-lock icon"></i> <input id="password" name="password"
					type="password" class="input" placeholder="Nhập mật khẩu" required />
			</div>

			<div class="options">
				<label><input type="checkbox" name="remember" /> Nhớ tôi</label> <a
					href="${pageContext.request.contextPath}/forgot-password">Quên
					mật khẩu?</a>
			</div>

			<button type="submit" class="btn">Đăng nhập</button>
		</form>

		<p class="register-text">
			Chưa có tài khoản? <a
				href="${pageContext.request.contextPath}/register">Đăng ký</a>
		</p>
	</div>

</body>
</html>
