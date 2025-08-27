<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div>
  <c:choose>
    <c:when test="${sessionScope.account == null}">
      <a href="${pageContext.request.contextPath}/login.jsp">Đăng nhập</a> |
      <a href="${pageContext.request.contextPath}/register.jsp">Đăng ký</a>
    </c:when>
    <c:otherwise>
      <span>Xin chào, ${sessionScope.account.fullname}</span> |
      <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
    </c:otherwise>
  </c:choose>
</div>
