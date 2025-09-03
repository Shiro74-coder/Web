<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <title>Đăng nhập</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"/>
  <style>
    body { background:#f6f7fb; }
    .login-card {
      max-width:420px; margin:60px auto; padding:32px;
      background:#fff; border-radius:16px; box-shadow:0 10px 30px rgba(0,0,0,.06);
    }
    .login-title { font-weight:600; text-align:center; margin-bottom:22px; }
    .input-group-text { width:42px; justify-content:center; }
    .brand-note { font-size:.9rem; color:#6c757d; text-align:center; margin-top:14px; }
  </style>
</head>
<body>

  <div class="login-card">
    <h5 class="login-title">Đăng Nhập Vào Hệ Thống</h5>

    <!-- FLASH thành công (logout / register / reset password / login) -->
    <c:if test="${not empty sessionScope.flash_success}">
      <div class="alert alert-success" role="alert">${sessionScope.flash_success}</div>
      <c:remove var="flash_success" scope="session"/>
    </c:if>

    <!-- Lỗi -->
    <c:if test="${not empty alert}">
      <div class="alert alert-danger" role="alert">${alert}</div>
    </c:if>

    <!-- Form đăng nhập -->
    <form action="<c:url value='/login'/>" method="post" autocomplete="on">
      <div class="mb-3">
        <label class="form-label">Tài khoản</label>
        <div class="input-group">
          <span class="input-group-text"><i class="fa fa-user"></i></span>
          <input type="text" name="username" class="form-control"
                 value="${cookie.username.value}" placeholder="Nhập tài khoản" required />
        </div>
      </div>

      <div class="mb-3">
        <label class="form-label">Mật khẩu</label>
        <div class="input-group">
          <span class="input-group-text"><i class="fa fa-lock"></i></span>
          <input type="password" name="password" class="form-control"
                 placeholder="Nhập mật khẩu" required />
        </div>
      </div>

      <div class="d-flex justify-content-between align-items-center mb-3">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" id="remember" name="remember">
          <label class="form-check-label" for="remember">Nhớ tôi</label>
        </div>
        <a href="<c:url value='/forgot-password'/>" class="text-decoration-none">Quên mật khẩu?</a>
      </div>

      <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>

      <div class="brand-note">
        Nếu bạn chưa có tài khoản, hãy
        <a href="<c:url value='/register'/>">Đăng ký</a>
      </div>
    </form>
  </div>

</body>
</html>
