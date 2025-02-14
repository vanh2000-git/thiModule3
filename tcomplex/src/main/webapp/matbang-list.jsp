<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ page import="com.example.tcomplex.model.MatBang" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh Sách Mặt Bằng Đang Cho Thuê</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow-lg">
        <div class="card-header bg-primary text-white text-center">
            <h2>Danh Sách Mặt Bằng Đang Cho Thuê</h2>
        </div>
        <div class="card-body">
            <form method="get" action="/matbang/list" class="row g-3">
                <div class="col-md-4">
                    <label class="form-label">Loại mặt bằng:</label>
                    <select name="loaiMatBang" class="form-select">
                        <option value="">Tất cả</option>
                        <c:forEach var="lmb" items="<%= MatBang.LoaiMatBang.values() %>">
                            <option value="${lmb.value}" <c:if test="${param.loaiMatBang eq lmb.value}">selected</c:if>>${lmb.value}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Giá tiền tối đa (VNĐ):</label>
                    <input type="number" name="giaTien" class="form-control" value="${param.giaTien}">
                </div>
                <div class="col-md-4">
                    <label class="form-label">Tầng:</label>
                    <select name="tang" class="form-select">
                        <option value="">Tất cả</option>
                        <c:forEach var="i" begin="1" end="15">
                            <option value="${i}" <c:if test="${param.tang eq i.toString()}">selected</c:if>>${i}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-12 text-center">
                    <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                </div>
            </form>

            <hr>

            <c:if test="${not empty matBangList}">
                <table class="table table-bordered table-striped">
                    <thead class="table-dark">
                    <tr>
                        <th>Mã Mặt Bằng</th>
                        <th>Diện Tích (m²)</th>
                        <th>Tầng</th>
                        <th>Loại Mặt Bằng</th>
                        <th>Giá Tiền (VNĐ)</th>
                        <th>Ngày Bắt Đầu</th>
                        <th>Ngày Kết Thúc</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="mb" items="${matBangList}">
                        <tr>
                            <td>${mb.maMatBang}</td>
                            <td>${mb.dienTich}</td>
                            <td>${mb.tang}</td>
                            <td>${mb.loaiMatBang.value}</td>
                            <td>${mb.giaTien}</td>
                            <td>${mb.ngayBatDau}</td>
                            <td>${mb.ngayKetThuc}</td>
                            <td>
                                <button class="btn btn-danger btn-sm" onclick="confirmDelete('${mb.maMatBang}')">
                                    Xóa
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty matBangList}">
                <div class="alert alert-warning text-center">Không có mặt bằng nào phù hợp!</div>
            </c:if>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function confirmDelete(maMatBang) {
        if (confirm("Bạn có chắc chắn muốn xóa mặt bằng với mã số " + maMatBang + " không?")) {
            window.location.href = "/matbang/list?action=delete&maMatBang=" + maMatBang;
        }
    }
</script>
</body>
</html>
