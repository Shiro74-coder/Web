<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <title>Danh mục của tôi</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

  <jsp:include page="topbar.jsp"/>

  <div class="container mt-3">
    <!-- flash -->
    <c:if test="${not empty sessionScope.flash_success}">
      <div class="alert alert-success" role="alert">${sessionScope.flash_success}</div>
      <c:remove var="flash_success" scope="session"/>
    </c:if>

    <div class="d-flex justify-content-between align-items-center mb-2">
      <h4 class="mb-0">Danh mục của tôi</h4>
      <a class="btn btn-primary" href="${pageContext.request.contextPath}/category/create">+ Thêm danh mục</a>
    </div>

    <c:choose>
      <c:when test="${empty categories}">
        <div class="alert alert-secondary">Chưa có danh mục nào.</div>
      </c:when>
      <c:otherwise>
        <table class="table table-striped">
          <thead>
            <tr><th>#</th><th>Tên danh mục</th><th>Ghi chú</th><th class="text-end">Hành động</th></tr>
          </thead>
          <tbody>
          <c:forEach var="c" items="${categories}" varStatus="st">
            <tr>
              <td>${st.index + 1}</td>
              <td>${c.name}</td>
              <td>${c.note}</td>
              <td class="text-end">
                <a class="btn btn-sm btn-outline-primary"
                   href="${pageContext.request.contextPath}/category/edit?id=${c.id}">Sửa</a>
                <form class="d-inline" action="${pageContext.request.contextPath}/category/delete" method="post"
                      onsubmit="return confirm('Xóa danh mục này?')">
                  <input type="hidden" name="id" value="${c.id}">
                  <button class="btn btn-sm btn-outline-danger" type="submit">Xóa</button>
                </form>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </c:otherwise>
    </c:choose>
  </div>
</body>
</html>
