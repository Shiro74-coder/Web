<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Đặt lại mật khẩu</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen flex items-center justify-center bg-gray-100">
  <div class="bg-white w-full max-w-md p-6 rounded-xl shadow">
    <h1 class="text-2xl font-semibold mb-2">Đặt lại mật khẩu</h1>
    <p class="text-sm text-gray-500 mb-4">Nhập mật khẩu mới cho tài khoản của bạn.</p>

    <c:if test="${alert != null}">
      <div class="mb-4 p-3 text-sm text-red-700 bg-red-100 rounded">${alert}</div>
    </c:if>

    <form action="${pageContext.request.contextPath}/reset" method="post" class="space-y-4">
      <input type="hidden" name="token" value="<c:out value='${token}'/>"/>
      <div>
        <label class="block text-sm text-gray-700">Mật khẩu mới</label>
        <input type="password" name="password" required minlength="6"
               class="w-full mt-1 px-3 py-2 border rounded focus:ring-2 focus:ring-indigo-500"/>
      </div>
      <div>
        <label class="block text-sm text-gray-700">Nhập lại mật khẩu</label>
        <input type="password" name="confirm" required minlength="6"
               class="w-full mt-1 px-3 py-2 border rounded focus:ring-2 focus:ring-indigo-500"/>
      </div>
      <button class="w-full bg-indigo-600 hover:bg-indigo-700 text-white py-2 rounded">Cập nhật</button>
    </form>

    <p class="text-center text-sm text-gray-600 mt-4">
      <a href="${pageContext.request.contextPath}/login" class="text-indigo-600 hover:underline">Quay lại đăng nhập</a>
    </p>
  </div>
</body>
</html>
