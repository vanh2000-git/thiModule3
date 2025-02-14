package com.example.tcomplex.service;

import com.example.tcomplex.model.MatBang;
import com.example.tcomplex.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatBangService {

    public boolean checkMaMatBangExists(String maMatBang) throws SQLException {
        String query = "SELECT COUNT(*) FROM MatBang WHERE maMatBang = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, maMatBang);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    public List<MatBang> getMatBangDangChoThue(String loaiMatBang, Long giaTien, Integer tang) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM MatBang Where 1=1");

        if (loaiMatBang != null && !loaiMatBang.isEmpty()) {
            query.append(" AND loaiMatBang = ?");
        }
        if (giaTien != null) {
            query.append(" AND giaTien <= ?");
        }
        if (tang != null) {
            query.append(" AND tang = ?");
        }
        query.append(" ORDER BY dienTich ASC");

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query.toString())) {

            int index = 1;
            if (loaiMatBang != null && !loaiMatBang.isEmpty()) {
                pstmt.setString(index++, loaiMatBang);
            }
            if (giaTien != null) {
                pstmt.setLong(index++, giaTien);
            }
            if (tang != null) {
                pstmt.setInt(index++, tang);
            }

            ResultSet rs = pstmt.executeQuery();
            List<MatBang> resultList = new ArrayList<>();
            while (rs.next()) {
                resultList.add(new MatBang(
                        rs.getString("maMatBang"),
                        MatBang.TrangThai.fromString(rs.getString("trangThai")),
                        rs.getDouble("dienTich"),
                        rs.getInt("tang"),
                        MatBang.LoaiMatBang.fromString(rs.getString("loaiMatBang")),
                        rs.getLong("giaTien"),
                        rs.getDate("ngayBatDau"),
                        rs.getDate("ngayKetThuc")
                ));
            }
            return resultList;
        }
    }

    public void deleteMatBang(String maMatBang) throws SQLException {
        String query = "DELETE FROM MatBang WHERE maMatBang = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, maMatBang);
            pstmt.executeUpdate();
        }
    }


    public boolean addMatBang(MatBang mb) throws SQLException {
        String query = "INSERT INTO MatBang (maMatBang, trangThai, dienTich, tang, loaiMatBang, giaTien, ngayBatDau, ngayKetThuc) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, mb.getMaMatBang());
            pstmt.setString(2, mb.getTrangThai().getValue());
            pstmt.setDouble(3, mb.getDienTich());
            pstmt.setInt(4, mb.getTang());
            pstmt.setString(5, mb.getLoaiMatBang().getValue());
            pstmt.setLong(6, mb.getGiaTien());
            pstmt.setDate(7, mb.getNgayBatDau());
            pstmt.setDate(8, mb.getNgayKetThuc());

            return pstmt.executeUpdate() > 0;
        }
    }
}