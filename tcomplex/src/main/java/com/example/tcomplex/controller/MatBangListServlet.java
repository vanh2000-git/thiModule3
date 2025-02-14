package com.example.tcomplex.controller;

import com.example.tcomplex.model.MatBang;
import com.example.tcomplex.service.MatBangService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/matbang/list")
public class MatBangListServlet extends HttpServlet {
    private MatBangService matBangService;

    @Override
    public void init() {
        matBangService = new MatBangService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            String maMatBang = request.getParameter("maMatBang");
            try {
                matBangService.deleteMatBang(maMatBang);
                response.sendRedirect("/matbang/list");
                return;
            } catch (SQLException e) {
                throw new ServletException("Lỗi khi xóa mặt bằng", e);
            }
        }

        String loaiMatBang = request.getParameter("loaiMatBang");
        String giaTienStr = request.getParameter("giaTien");
        String tangStr = request.getParameter("tang");

        Long giaTien = (giaTienStr != null && !giaTienStr.isEmpty()) ? Long.parseLong(giaTienStr) : null;
        Integer tang = (tangStr != null && !tangStr.isEmpty()) ? Integer.parseInt(tangStr) : null;

        try {
            List<MatBang> matBangList = matBangService.getMatBangDangChoThue(loaiMatBang, giaTien, tang);
            request.setAttribute("matBangList", matBangList);
        } catch (SQLException e) {
            throw new ServletException("Lỗi khi lấy danh sách mặt bằng", e);
        }

        request.getRequestDispatcher("/matbang-list.jsp").forward(request, response);
    }
}