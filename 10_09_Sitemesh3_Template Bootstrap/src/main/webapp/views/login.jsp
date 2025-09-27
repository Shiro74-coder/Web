<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="min-h-screen flex items-center justify-center bg-gradient-to-r from-indigo-500 via-purple-500 to-pink-500">

  <div class="bg-white/90 backdrop-blur-md p-8 rounded-2xl shadow-2xl w-full max-w-md">
    <h2 class="text-3xl font-bold text-center text-gray-800 mb-6">Welcome Back 👋</h2>

    <form action="${pageContext.request.contextPath}/login" method="post" class="space-y-5">
      <c:if test="${alert != null}">
        <div class="p-3 mb-4 text-sm text-red-700 bg-red-100 rounded-lg">${alert}</div>
      </c:if>

      <div>
        <label for="username" class="block text-sm font-medium text-gray-700">Username</label>
        <input type="text" id="username" name="username" required
               class="w-full mt-1 px-4 py-2 border rounded-lg shadow-sm focus:ring-2 focus:ring-indigo-500 focus:outline-none"
               placeholder="Enter your username">
      </div>

      <div>
        <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
        <input type="password" id="password" name="password" required
               class="w-full mt-1 px-4 py-2 border rounded-lg shadow-sm focus:ring-2 focus:ring-indigo-500 focus:outline-none"
               placeholder="••••••••">
      </div>

      <!-- Remember + Forgot -->
      <div class="flex items-center justify-between text-sm">
        <label class="flex items-center gap-2">
          <input type="checkbox" name="remember"
                 class="rounded text-indigo-600 focus:ring-indigo-500">
          Remember me
        </label>

        <!-- Nút/Link quên mật khẩu -->
        <a href="${pageContext.request.contextPath}/forgot"
           class="text-indigo-600 hover:underline">Forgot password?</a>
      </div>

      <button type="submit"
              class="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-2 px-4 rounded-lg shadow-md transition">
        Sign In
      </button>
    </form>

    <p class="mt-6 text-center text-sm text-gray-600">
      Don’t have an account?
      <a href="${pageContext.request.contextPath}/register"
         class="text-indigo-600 hover:underline font-medium">Sign up</a>
    </p>
  </div>

</body>
</html>
