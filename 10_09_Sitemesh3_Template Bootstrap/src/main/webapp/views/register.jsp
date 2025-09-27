<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8" />
  <title>Đăng ký tài khoản</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen flex items-center justify-center bg-gradient-to-r from-indigo-500 via-purple-500 to-pink-500">

  <div class="bg-white/90 backdrop-blur-md p-8 rounded-2xl shadow-2xl w-full max-w-md">
    <h2 class="text-3xl font-bold text-center text-gray-800 mb-2">Tạo tài khoản mới</h2>
    <p class="text-center text-sm text-gray-500 mb-6">Tham gia cùng chúng tôi ngay hôm nay ✨</p>

    <!-- Alert chung -->
    <c:if test="${alert != null}">
      <div class="mb-5 p-3 text-sm text-red-700 bg-red-100 rounded-lg">
        ${alert}
      </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/register" method="post" class="space-y-4">
      <!-- Username -->
      <div>
        <label class="block text-sm font-medium text-gray-700" for="username">Tài khoản</label>
        <input
          id="username" name="username" type="text" required
          value="<c:out value='${param.username}'/>"
          class="w-full mt-1 px-4 py-2 border rounded-lg shadow-sm focus:ring-2 focus:ring-indigo-500 focus:outline-none"
          placeholder="Tên đăng nhập" />
      </div>

      <!-- Email -->
      <div>
        <label class="block text-sm font-medium text-gray-700" for="email">Email</label>
        <input
          id="email" name="email" type="email" required
          value="<c:out value='${param.email}'/>"
          class="w-full mt-1 px-4 py-2 border rounded-lg shadow-sm focus:ring-2 focus:ring-indigo-500 focus:outline-none"
          placeholder="you@example.com" />
      </div>

      <!-- Họ tên -->
      <div>
        <label class="block text-sm font-medium text-gray-700" for="fullname">Họ và tên</label>
        <input
          id="fullname" name="fullname" type="text"
          value="<c:out value='${param.fullname}'/>"
          class="w-full mt-1 px-4 py-2 border rounded-lg shadow-sm focus:ring-2 focus:ring-indigo-500 focus:outline-none"
          placeholder="Nguyễn Văn A" />
      </div>

      <!-- SĐT -->
      <div>
        <label class="block text-sm font-medium text-gray-700" for="phone">Số điện thoại</label>
        <input
          id="phone" name="phone" type="tel" pattern="^[0-9]{9,11}$"
          value="<c:out value='${param.phone}'/>"
          class="w-full mt-1 px-4 py-2 border rounded-lg shadow-sm focus:ring-2 focus:ring-indigo-500 focus:outline-none"
          placeholder="098xxxxxxx" />
      </div>

      <!-- Password -->
      <div>
        <label class="block text-sm font-medium text-gray-700" for="password">Mật khẩu</label>
        <input
          id="password" name="password" type="password" required minlength="6"
          class="w-full mt-1 px-4 py-2 border rounded-lg shadow-sm focus:ring-2 focus:ring-indigo-500 focus:outline-none"
          placeholder="••••••••" />
      </div>

      <!-- Confirm -->
      <div>
        <label class="block text-sm font-medium text-gray-700" for="confirm">Nhập lại mật khẩu</label>
        <input
          id="confirm" name="confirm" type="password" required minlength="6"
          class="w-full mt-1 px-4 py-2 border rounded-lg shadow-sm focus:ring-2 focus:ring-indigo-500 focus:outline-none"
          placeholder="••••••••" />
      </div>

      <!-- Điều khoản -->
      <div class="flex items-start gap-2 text-sm">
        <input id="terms" name="terms" type="checkbox" required class="mt-1 rounded text-indigo-600 focus:ring-indigo-500" />
        <label for="terms" class="text-gray-600">
          Tôi đồng ý với <a href="#" class="text-indigo-600 hover:underline">Điều khoản sử dụng</a>.
        </label>
      </div>

      <!-- Submit -->
      <button type="submit"
        class="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-2.5 px-4 rounded-lg shadow-md transition">
        Đăng ký
      </button>
    </form>

    <p class="mt-6 text-center text-sm text-gray-600">
      Đã có tài khoản?
      <a href="${pageContext.request.contextPath}/login" class="text-indigo-600 hover:underline font-medium">Đăng nhập</a>
    </p>
  </div>
</body>
</html>
