package com.example.tcomplex.controller;

import com.example.tcomplex.model.MatBang;
import com.example.tcomplex.service.MatBangService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/matbang/add")
public class MatBangServlet extends HttpServlet {
    private MatBangService matBangService;

    @Override
    public void init() {
        matBangService = new MatBangService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/matbang-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maMatBang = request.getParameter("maMatBang");
        String trangThai = request.getParameter("trangThai");
        double dienTich = Double.parseDouble(request.getParameter("dienTich"));
        int tang = Integer.parseInt(request.getParameter("tang"));
        String loaiMatBang = request.getParameter("loaiMatBang");
        long giaTien = Long.parseLong(request.getParameter("giaTien"));
        Date ngayBatDau = Date.valueOf(request.getParameter("ngayBatDau"));
        Date ngayKetThuc = Date.valueOf(request.getParameter("ngayKetThuc"));

        try {
            if (matBangService.checkMaMatBangExists(maMatBang)) {
                request.setAttribute("error", "Mã mặt bằng vừa thêm đã tồn tại!");
                request.getRequestDispatcher("/matbang-form.jsp").forward(request, response);
                return;
            }
        } catch (SQLException e) {
            throw new ServletException("Lỗi khi kiểm tra mã mặt bằng", e);
        }

        long diff = ngayKetThuc.getTime() - ngayBatDau.getTime();
        if (diff < 15552000000L) {
            request.setAttribute("error", "Ngày kết thúc phải lớn hơn ngày bắt đầu ít nhất 6 tháng!");
            request.getRequestDispatcher("/matbang-form.jsp").forward(request, response);
            return;
        }

        MatBang newMatBang = new MatBang(
                maMatBang,
                MatBang.TrangThai.fromString(trangThai),
                dienTich,
                tang,
                MatBang.LoaiMatBang.fromString(loaiMatBang),
                giaTien,
                ngayBatDau,
                ngayKetThuc
        );

        try {
            if (matBangService.addMatBang(newMatBang)) {
                response.sendRedirect("/matbang/list");
            } else {
                request.setAttribute("error", "Thêm mặt bằng thất bại!");
                request.getRequestDispatcher("/matbang-form.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Lỗi khi thêm mặt bằng", e);
        }
    }
}