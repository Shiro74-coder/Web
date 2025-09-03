<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="conn.Account" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    Account acc = (Account) session.getAttribute("account");
    if (acc == null) {
        response.sendRedirect(request.getContextPath()+"/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <title>Admin - Trang chủ</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
  <jsp:include page="/topbar.jsp"/>

  <div class="container mt-3">
    <c:if test="${not empty sessionScope.flash_success}">
      <div class="alert alert-success" role="alert">${sessionScope.flash_success}</div>
      <c:remove var="flash_success" scope="session"/>
    </c:if>
  </div>

  <div class="container mt-3">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title mb-3">Khu vực Quản trị</h4>
        <p class="mb-1">Xin chào, <strong><%= acc.getFullname() %></strong> (ADMIN)</p>
        <hr>
        <a class="btn btn-outline-secondary" href="<%= request.getContextPath() %>/logout">Đăng xuất</a>
      </div>
    </div>
  </div>
</body>
</html>
