<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <title>Đặt lại mật khẩu</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <style>
    body{background:#f6f7fb} .card-box{max-width:520px;margin:50px auto;background:#fff;
    padding:28px;border-radius:16px;box-shadow:0 10px 30px rgba(0,0,0,.06)}
  </style>
</head>
<body>
<div class="card-box">
  <h4 class="mb-3">Đặt lại mật khẩu</h4>
  <c:if test="${not empty alert}">
    <div class="alert alert-danger" role="alert">${alert}</div>
  </c:if>
  <form action="<c:url value='/reset-password'/>" method="post">
    <div class="mb-3">
      <label class="form-label">Mật khẩu mới</label>
      <input type="password" name="password" class="form-control" required/>
    </div>
    <div class="mb-3">
      <label class="form-label">Nhập lại mật khẩu</label>
      <input type="password" name="retype" class="form-control" required/>
    </div>
    <button class="btn btn-primary w-100" type="submit">Đổi mật khẩu</button>
    <div class="mt-3">
      <a href="<c:url value='/login.jsp'/>">Quay lại đăng nhập</a>
    </div>
  </form>
</div>
</body>
</html>
