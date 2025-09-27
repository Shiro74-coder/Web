<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard</title>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
  <div class="min-h-screen flex">

    <!-- Sidebar -->
    <aside class="w-64 bg-white border-r hidden md:flex md:flex-col">
      <div class="px-6 py-4 border-b">
        <a href="${pageContext.request.contextPath}/admin" class="text-xl font-bold">Admin Panel</a>
        <p class="text-sm text-gray-500 mt-1">
          Xin chào,
          <span class="font-medium">
            <c:out value="${empty sessionScope.account.fullname ? sessionScope.account.username : sessionScope.account.fullname}"/>
          </span>
        </p>
      </div>
      <nav class="flex-1 px-3 py-4 space-y-1 text-sm">
        <a class="block px-3 py-2 rounded-lg bg-indigo-50 text-indigo-700 font-medium" href="${pageContext.request.contextPath}/admin">🏠 Trang chủ</a>
        <a class="block px-3 py-2 rounded-lg hover:bg-gray-50" href="${pageContext.request.contextPath}/admin/users">👤 Người dùng</a>
        <a class="block px-3 py-2 rounded-lg hover:bg-gray-50" href="${pageContext.request.contextPath}/admin/orders">🧾 Đơn hàng</a>
        <a class="block px-3 py-2 rounded-lg hover:bg-gray-50" href="${pageContext.request.contextPath}/admin/products">📦 Sản phẩm</a>
        <a class="block px-3 py-2 rounded-lg hover:bg-gray-50" href="${pageContext.request.contextPath}/admin/reports">📊 Báo cáo</a>
        <a class="block px-3 py-2 rounded-lg hover:bg-gray-50" href="${pageContext.request.contextPath}/admin/settings">⚙️ Cấu hình</a>
      </nav>
      <div class="px-4 py-4 border-t">
        <a href="${pageContext.request.contextPath}/logout"
           class="w-full inline-flex items-center justify-center gap-2 px-4 py-2 bg-gray-100 hover:bg-gray-200 rounded-lg">
          Đăng xuất
        </a>
      </div>
    </aside>

    <!-- Main -->
    <main class="flex-1">
      <!-- Header -->
      <header class="bg-white border-b">
        <div class="mx-auto max-w-7xl px-4 py-3 flex items-center justify-between">
          <h1 class="text-xl font-semibold">Trang chủ quản trị</h1>
          <a href="${pageContext.request.contextPath}/logout"
             class="inline-flex items-center px-3 py-1.5 rounded-lg border hover:bg-gray-50">Đăng xuất</a>
        </div>
      </header>

      <!-- Toolbar (đã bỏ 'Làm mới') -->
      <c:set var="cxt" value="${pageContext.request.contextPath}"/>
      <div class="mx-auto max-w-7xl px-4 mt-4">
        <div class="flex flex-wrap items-center gap-2">
          <a href="${cxt}/admin/users/add" class="px-3 py-2 rounded-lg bg-indigo-600 text-white hover:bg-indigo-700">+ Thêm người dùng</a>
          <a href="${cxt}/admin/products/add" class="px-3 py-2 rounded-lg bg-indigo-500 text-white hover:bg-indigo-600">+ Thêm sản phẩm</a>
          <a href="${cxt}/member/categories/add" class="px-3 py-2 rounded-lg bg-emerald-600 text-white hover:bg-emerald-700">+ Thêm danh mục</a>
          <a href="${cxt}/logout" class="px-3 py-2 rounded-lg border hover:bg-gray-50">Đăng xuất</a>
        </div>
      </div>

      <!-- Content -->
      <div class="mx-auto max-w-7xl p-4 md:p-6 space-y-6">
        <!-- KPI -->
        <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
          <div class="bg-white rounded-xl shadow p-4"><p class="text-sm text-gray-500">Người dùng</p><p class="text-2xl font-bold mt-1"><c:out value="${statsUsers}"/></p></div>
          <div class="bg-white rounded-xl shadow p-4"><p class="text-sm text-gray-500">Đơn hàng</p><p class="text-2xl font-bold mt-1"><c:out value="${statsOrders}"/></p></div>
          <div class="bg-white rounded-xl shadow p-4"><p class="text-sm text-gray-500">Doanh thu</p><p class="text-2xl font-bold mt-1"><c:out value="${statsRevenue}"/></p></div>
          <div class="bg-white rounded-xl shadow p-4"><p class="text-sm text-gray-500">Chờ xử lý</p><p class="text-2xl font-bold mt-1"><c:out value="${statsPending}"/></p></div>
        </div>

        <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
          <div class="lg:col-span-2 bg-white rounded-xl shadow">
            <div class="px-5 py-4 border-b flex items-center justify-between">
              <h2 class="text-lg font-semibold">Người dùng mới</h2>
              <!-- ĐÃ BỎ 'Xem tất cả' -->
            </div>
            <div class="p-5 overflow-x-auto">
              <table class="min-w-full text-sm">
                <thead><tr class="text-left text-gray-500">
                  <th class="py-2 pr-4">ID</th><th class="py-2 pr-4">Username</th><th class="py-2 pr-4">Email</th><th class="py-2">Vai trò</th>
                </tr></thead>
                <tbody class="text-gray-800">
                  <c:forEach var="u" items="${recentUsers}">
                    <tr class="border-t">
                      <td class="py-2 pr-4"><c:out value="${u.id}"/></td>
                      <td class="py-2 pr-4"><c:out value="${u.username}"/></td>
                      <td class="py-2 pr-4"><c:out value="${u.email}"/></td>
                      <td class="py-2"><span class="inline-flex px-2 py-0.5 rounded-full text-xs bg-indigo-100 text-indigo-700">
                        <c:out value="${u.roleid == 1 ? 'Admin' : (u.roleid == 3 ? 'Manager' : 'User')}"/></span></td>
                    </tr>
                  </c:forEach>
                  <c:if test="${empty recentUsers}">
                    <tr><td colspan="4" class="py-6 text-center text-gray-500">Chưa có dữ liệu.</td></tr>
                  </c:if>
                </tbody>
              </table>
            </div>
          </div>
          <div class="bg-white rounded-xl shadow">
            <div class="px-5 py-4 border-b"><h2 class="text-lg font-semibold">Hoạt động gần đây</h2></div>
            <div class="p-5 space-y-4">
              <div class="text-sm text-gray-700">• Đồng bộ báo cáo doanh thu sáng nay.</div>
              <div class="text-sm text-gray-700">• 5 đơn hàng mới được tạo.</div>
              <div class="text-sm text-gray-700">• 2 sản phẩm sắp hết hàng.</div>
            </div>
          </div>
        </div>
      </div>
    </main>

  </div>
</body>
</html>
