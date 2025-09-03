<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"/>
  <title><c:out value="${mode=='edit' ? 'Sửa danh mục' : 'Thêm danh mục'}"/></title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

  <jsp:include page="topbar.jsp"/>

  <div class="container mt-3" style="max-width:720px;">
    <h4 class="mb-3"><c:out value="${mode=='edit' ? 'Sửa danh mục' : 'Thêm danh mục'}"/></h4>

    <c:if test="${not empty alert}">
      <div class="alert alert-danger" role="alert">${alert}</div>
    </c:if>

    <form method="post"
          action="${pageContext.request.contextPath}${mode=='edit' ? '/category/edit' : '/category/create'}">
      <c:if test="${mode=='edit'}">
        <input type="hidden" name="id" value="${category.id}"/>
      </c:if>

      <div class="mb-3">
        <label class="form-label">Tên danh mục *</label>
        <input name="name" class="form-control" required
               value="${mode=='edit' ? category.name : ''}"/>
      </div>

      <div class="mb-3">
        <label class="form-label">Ghi chú</label>
        <textarea name="note" class="form-control" rows="3">${mode=='edit' ? category.note : ''}</textarea>
      </div>

      <div class="d-flex gap-2">
        <button class="btn btn-primary" type="submit">Lưu</button>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/category">Hủy</a>
      </div>
    </form>
  </div>
</body>
</html>
