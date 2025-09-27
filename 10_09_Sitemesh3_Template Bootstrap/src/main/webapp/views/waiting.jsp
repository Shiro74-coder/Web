<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Please Wait...</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <script>
    // Tự động chuyển sau 3 giây
    setTimeout(function() {
      window.location.href = "<%= request.getContextPath() %>/home";
    }, 3000);
  </script>
</head>
<body class="h-screen flex flex-col items-center justify-center bg-gradient-to-r from-green-400 via-blue-500 to-purple-600 text-white">

  <div class="text-center">
    <!-- Vòng xoay loading -->
    <div class="w-16 h-16 border-4 border-white border-dashed rounded-full animate-spin mx-auto"></div>
    
    <h2 class="mt-6 text-3xl font-bold">Đang xử lý...</h2>
    <p class="mt-2 text-lg">Xin vui lòng chờ trong giây lát 🚀</p>
  </div>

</body>
</html>
