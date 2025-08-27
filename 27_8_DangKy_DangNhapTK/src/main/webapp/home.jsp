<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="conn.Account" %>
<%
    Account acc = (Account) session.getAttribute("account");
    if (acc == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Trang chủ</title></head>
<body>
  <jsp:include page="topbar.jsp"/>
  <h2>Xin chào <%= acc.getFullname() %> (Vai trò: <%= acc.getRole() %>)</h2>
  <a href="logout">Đăng xuất</a>
</body>
</html>
