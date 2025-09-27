<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Manager Console</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-50">
  <header class="bg-white border-b">
    <div class="max-w-7xl mx-auto px-4 py-3 flex items-center justify-between">
      <div>
        <h1 class="text-xl font-semibold">Bảng điều khiển Manager</h1>
        <p class="text-sm text-gray-500">
          Xin chào,
          <span class="font-medium"><c:out value="${empty sessionScope.account.fullname ? sessionScope.account.username : sessionScope.account.fullname}"/></span>
        </p>
      </div>
      <a href="${pageContext.request.contextPath}/logout"
         class="inline-flex items-center px-3 py-1.5 rounded-lg border hover:bg-gray-50">Đăng xuất</a>
    </div>
  </header>

  <!-- Toolbar (đã bỏ 'Công việc hôm nay' & 'Làm mới') -->
  <c:set var="cxt" value="${pageContext.request.contextPath}"/>
  <div class="max-w-7xl mx-auto px-4 mt-4">
    <div class="flex flex-wrap items-center gap-2">
      <a href="${cxt}/member/categories" class="px-3 py-2 rounded-lg bg-emerald-600 text-white hover:bg-emerald-700">Danh mục của tôi</a>
      <a href="${cxt}/member/categories/add" class="px-3 py-2 rounded-lg bg-emerald-500 text-white hover:bg-emerald-600">+ Thêm danh mục</a>
      <a href="${cxt}/logout" class="px-3 py-2 rounded-lg border hover:bg-gray-50">Đăng xuất</a>
    </div>
  </div>

  <main class="max-w-7xl mx-auto p-4 md:p-6 space-y-6">
    <div class="grid grid-cols-1 md:grid-cols-3 gap-4">
      <div class="bg-white rounded-xl shadow p-4"><p class="text-sm text-gray-500">Sản phẩm</p><p class="text-2xl font-bold mt-1"><c:out value="${statsProducts}"/></p></div>
      <div class="bg-white rounded-xl shadow p-4"><p class="text-sm text-gray-500">Đơn chờ duyệt</p><p class="text-2xl font-bold mt-1"><c:out value="${statsPending}"/></p></div>
      <div class="bg-white rounded-xl shadow p-4"><p class="text-sm text-gray-500">Kho sắp hết</p><p class="text-2xl font-bold mt-1"><c:out value="${statsLowStock}"/></p></div>
    </div>

    <div class="bg-white rounded-xl shadow">
      <div class="px-5 py-4 border-b flex items-center justify-between">
        <h2 class="text-lg font-semibold">Công việc hôm nay</h2>
        <!-- ĐÃ BỎ 'Xem tất cả' -->
      </div>
      <div class="p-5 space-y-3 text-sm text-gray-700">
        <div>• Kiểm tra đơn chờ duyệt</div>
        <div>• Cập nhật tồn kho sản phẩm mới</div>
        <div>• Xem báo cáo doanh số theo ca</div>
      </div>
    </div>
  </main>
</body>
</html>
