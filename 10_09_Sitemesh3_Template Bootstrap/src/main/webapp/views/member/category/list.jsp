<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8"><title>Danh mục của tôi</title>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">
  <div class="max-w-5xl mx-auto p-6 space-y-6">
    <%-- ... phần header của trang ... --%>
    <div class="flex items-center justify-between gap-2 flex-wrap">
      <h1 class="text-2xl font-semibold">Danh mục của tôi</h1>
      <div class="flex items-center gap-2">
        <form method="get" class="flex items-center gap-2">
          <input name="q" value="<c:out value='${param.q}'/>" class="px-3 py-2 border rounded-lg" placeholder="Tìm theo tên..."/>
          <button class="px-3 py-2 rounded-lg bg-gray-100 hover:bg-gray-200" type="submit">Tìm</button>
        </form>
        <a href="<c:url value='/member/profile' />" class="px-3 py-2 rounded-lg border hover:bg-gray-50">⟵ Về Profile</a>
        <a href="<c:url value='/member/categories/add' />" class="px-3 py-2 rounded-lg bg-emerald-600 text-white hover:bg-emerald-700">+ Thêm danh mục</a>
        <a href="<c:url value='/logout' />" class="px-3 py-2 rounded-lg border hover:bg-gray-50">Đăng xuất</a>
      </div>
    </div>
    <div class="bg-white rounded-xl shadow overflow-x-auto">
      <table class="min-w-full text-sm">
        <%-- ... thead ... --%>
        <thead class="bg-gray-50 text-gray-600">
          <tr>
            <th class="text-left py-3 px-4">ID</th>
            <th class="py-3 px-4">Ảnh</th> 
            <th class="text-left py-3 px-4">Tên</th>
            <th class="text-left py-3 px-4">Ngày tạo</th>
            <th class="py-3 px-4">Thao tác</th>
          </tr>
        </thead>
        <tbody class="text-gray-800">
          <c:forEach var="c" items="${list}">
            <tr class="border-t align-middle">
              <td class="py-2 px-4"><c:out value="${c.id}"/></td>
              <td class="p-2 text-center">
                  <c:choose>
                      <c:when test="${not empty c.icon}">
                          <%-- DÙNG c:url ĐỂ TRỎ ĐÚNG VÀO ImageController --%>
                          <c:url var="imageUrl" value="/uploads/category/${c.icon}" />
                          <img src="${imageUrl}" alt="${c.name}" class="h-16 w-16 object-cover rounded-md inline-block">
                      </c:when>
                      <c:otherwise>
                          <span class="text-xs text-gray-500">Không có ảnh</span>
                      </c:otherwise>
                  </c:choose>
              </td>
              <td class="py-2 px-4"><c:out value="${c.name}"/></td>
              <td class="py-2 px-4"><c:out value="${c.createdDate}"/></td>
              <td class="py-2 px-4 text-center">
                <c:url var="editUrl" value="/member/categories/edit?id=${c.id}" />
                <a class="text-indigo-600 hover:underline" href="${editUrl}">Sửa</a>
                <span class="mx-1 text-gray-300">|</span>
                <c:url var="deleteUrl" value="/member/categories/delete?id=${c.id}" />
                <a class="text-rose-600 hover:underline" onclick="return confirm('Xóa danh mục này?')" href="${deleteUrl}">Xóa</a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</body>
</html>