<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.tcomplex.model.MatBang" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Thêm Mặt Bằng</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow-lg">
        <div class="card-header bg-primary text-white">
            <h2 class="text-center">Thêm Mặt Bằng Mới</h2>
        </div>
        <div class="card-body">
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <form action="add" method="post" accept-charset="UTF-8">
                <div class="mb-3">
                    <label class="form-label">Mã mặt bằng:</label>
                    <input type="text" name="maMatBang" class="form-control" pattern="[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Trạng thái:</label>
                    <select name="trangThai" class="form-select">
                        <c:forEach var="tt" items="<%= MatBang.TrangThai.values() %>">
                            <option value="${tt.value}" <c:if test="${tt.value eq param.trangThai}">selected</c:if>>${tt.value}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">Diện tích (m²):</label>
                    <input type="number" name="dienTich" class="form-control" min="20" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Tầng:</label>
                    <select name="tang" class="form-select">
                        <c:forEach var="i" begin="1" end="15">
                            <option value="${i}">${i}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">Loại mặt bằng:</label>
                    <select name="loaiMatBang" class="form-select">
                        <c:forEach var="lmb" items="<%= MatBang.LoaiMatBang.values() %>">
                            <option value="${lmb.value}">${lmb.value}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">Giá tiền (VNĐ):</label>
                    <input type="number" name="giaTien" class="form-control" min="1000000" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Ngày bắt đầu:</label>
                    <input type="date" name="ngayBatDau" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Ngày kết thúc:</label>
                    <input type="date" name="ngayKetThuc" class="form-control" required>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Thêm Mặt Bằng</button>
                    <a href="/matbang/list" class="btn btn-secondary">Quay lại</a>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap 5 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
