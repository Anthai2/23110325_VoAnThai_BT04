<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><body>
  <h2>Login success!</h2>
  <p>Xin chào,
    <%= ((vn.iostar.models.UserModel)session.getAttribute("account")) != null
        ? ((vn.iostar.models.UserModel)session.getAttribute("account")).getUsername()
        : "guest" %>
  </p>
</body></html>
