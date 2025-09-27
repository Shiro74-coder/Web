<%-- /webapp/views/user/profile.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Cập nhật thông tin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto max-w-2xl p-6 mt-10">
        <div class="bg-white p-8 rounded-lg shadow-md">
            <div class="flex items-center justify-between mb-6">
                 <h1 class="text-2xl font-bold text-gray-800">Thông Tin Cá Nhân</h1>
                 <a href="${pageContext.request.contextPath}/home" class="text-indigo-600 hover:underline">Về trang chủ</a>
            </div>

            <%-- Hiển thị thông báo --%>
            <c:if test="${not empty success}">
                <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4" role="alert">
                    <span class="block sm:inline">${success}</span>
                </div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert">
                    <span class="block sm:inline">${error}</span>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/member/profile" method="post" enctype="multipart/form-data">
                <div class="space-y-6">
                    <%-- Avatar hiện tại và input upload --%>
                    <div class="flex items-center space-x-6">
                        <div class="shrink-0">
                            <c:set var="avatarUrl" value="${pageContext.request.contextPath}/uploads/user/${sessionScope.account.images}" />
                            <c:if test="${empty sessionScope.account.images}">
                                <c:set var="avatarUrl" value="https://via.placeholder.com/100" />
                            </c:if>
                            <img class="h-24 w-24 object-cover rounded-full" src="${avatarUrl}" alt="Current Avatar" />
                        </div>
                        <label class="block">
                            <span class="sr-only">Chọn ảnh đại diện</span>
                            <input type="file" name="images" class="block w-full text-sm text-slate-500
                                file:mr-4 file:py-2 file:px-4
                                file:rounded-full file:border-0
                                file:text-sm file:font-semibold
                                file:bg-violet-50 file:text-violet-700
                                hover:file:bg-violet-100"
                            />
                        </label>
                    </div>

                    <%-- Username (Không cho sửa) --%>
                    <div>
                        <label class="block text-sm font-medium text-gray-700">Tên đăng nhập</label>
                        <input type="text" value="${sessionScope.account.username}" disabled
                               class="mt-1 block w-full px-3 py-2 bg-gray-100 border border-gray-300 rounded-md shadow-sm sm:text-sm">
                    </div>

                    <%-- Fullname --%>
                    <div>
                        <label for="fullname" class="block text-sm font-medium text-gray-700">Họ và tên</label>
                        <input type="text" id="fullname" name="fullname" value="${sessionScope.account.fullname}"
                               class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                    </div>

                    <%-- Phone --%>
                    <div>
                        <label for="phone" class="block text-sm font-medium text-gray-700">Số điện thoại</label>
                        <input type="tel" id="phone" name="phone" value="${sessionScope.account.phone}"
                               class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                    </div>
                </div>

                <div class="mt-8 flex items-center gap-4">
                    <button type="submit" class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                        Lưu thay đổi
                    </button>
                    
                    <a href="${pageContext.request.contextPath}/member/categories" class="w-full text-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-emerald-600 hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500">
                        Quản lý Danh mục
                    </a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>