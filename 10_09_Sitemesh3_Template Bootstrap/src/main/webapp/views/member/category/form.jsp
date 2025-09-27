<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title><c:out value="${empty category ? 'Thêm' : 'Sửa'}"/> danh mục</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">
<div class="max-w-lg mx-auto p-6 space-y-6 mt-10">
    <div class="flex items-center justify-between">
        <h1 class="text-2xl font-semibold">
            <c:out value="${empty category ? 'Thêm danh mục' : 'Sửa danh mục'}"/>
        </h1>
        <div class="flex items-center gap-2">
            <a href="${pageContext.request.contextPath}/member/profile" class="px-3 py-2 rounded-lg border hover:bg-gray-50">Về Profile</a>
            <a href="${pageContext.request.contextPath}/member/categories" class="px-3 py-2 rounded-lg border hover:bg-gray-50">Quay lại DS</a>
        </div>
    </div>

    <c:if test="${not empty alert}">
        <div class="p-3 text-sm text-red-700 bg-red-100 rounded"><c:out value="${alert}"/></div>
    </c:if>

    <form method="post" class="space-y-4 bg-white p-8 rounded-lg shadow-md"
          action="${pageContext.request.contextPath}${empty category ? '/member/categories/add' : '/member/categories/edit'}"
          enctype="multipart/form-data">
        
        <c:if test="${not empty category}">
            <input type="hidden" name="id" value="<c:out value='${category.id}'/>"/>
      
            <input type="hidden" name="oldIcon" value="<c:out value='${category.icon}'/>"/>
        </c:if>

        <div>
            <label for="name" class="block text-sm font-medium text-gray-700">Tên danh mục</label>
            <input id="name" name="name" required
                   value="<c:out value='${not empty category ? category.name : param.name}'/>"
                   class="w-full mt-1 px-3 py-2 border rounded focus:ring-2 focus:ring-indigo-500"/>
        </div>

        <div>
            <label class="block text-sm font-medium text-gray-700">Ảnh danh mục</label>
            <div class="mt-2 flex items-center gap-x-3">
                <%-- Khu vực hiển thị ảnh (xem trước) --%>
                <c:set var="imgSrc" value="https://via.placeholder.com/150" />
                <c:if test="${not empty category.icon}">
                    <c:url var="imgSrc" value="/uploads/category/${category.icon}" />
                </c:if>
                <img id="imagePreview" src="${imgSrc}" alt="Xem trước ảnh" class="h-24 w-24 object-cover rounded-md bg-gray-100">
                
                <%-- Input để chọn file --%>
                <input type="file" name="icon" id="iconInput" accept="image/*"
                       class="rounded-md bg-white px-2.5 py-1.5 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50">
            </div>
        </div>

        <button type="submit" class="w-full bg-indigo-600 hover:bg-indigo-700 text-white py-2 rounded-md">
            <c:out value="${empty category ? 'Thêm mới' : 'Cập nhật'}"/>
        </button>
    </form>
</div>

<%-- JavaScript để xử lý xem trước ảnh --%>
<script>
    const iconInput = document.getElementById('iconInput');
    const imagePreview = document.getElementById('imagePreview');

    iconInput.addEventListener('change', function(event) {
        // Lấy file mà người dùng đã chọn
        const file = event.target.files[0];

        if (file) {
            // Tạo một đối tượng FileReader để đọc file
            const reader = new FileReader();

            // Định nghĩa sự kiện onload, sẽ được kích hoạt khi file được đọc xong
            reader.onload = function(e) {
                // Gán dữ liệu của file (dưới dạng URL) vào thuộc tính src của thẻ img
                imagePreview.src = e.target.result;
            };

            reader.readAsDataURL(file);
        }
    });
</script>

</body>
</html>