package com.example.tcomplex.model;

import java.sql.Date;

public class MatBang {
    public enum TrangThai {
        TRONG("Trong"), HA_TANG("Ha Tang"), DAY_DU("Day Du");

        private final String value;

        TrangThai(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static TrangThai fromString(String text) {
            for (TrangThai tt : TrangThai.values()) {
                if (tt.value.equalsIgnoreCase(text)) {
                    return tt;
                }
            }
            throw new IllegalArgumentException("Không hợp lệ: " + text);
        }
    }

    public enum LoaiMatBang {
        VAN_PHONG_CHIA_SE("Van phong chia se"),
        VAN_PHONG_TRON_GOI("Van phong tron goi");

        private final String value;

        LoaiMatBang(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static LoaiMatBang fromString(String text) {
            for (LoaiMatBang lmb : LoaiMatBang.values()) {
                if (lmb.value.equalsIgnoreCase(text)) {
                    return lmb;
                }
            }
            throw new IllegalArgumentException("Không hợp lệ: " + text);
        }
    }

    private String maMatBang;
    private TrangThai trangThai;
    private double dienTich;
    private int tang;
    private LoaiMatBang loaiMatBang;
    private long giaTien;
    private Date ngayBatDau;
    private Date ngayKetThuc;

    public MatBang() {}

    public MatBang(String maMatBang, TrangThai trangThai, double dienTich, int tang, LoaiMatBang loaiMatBang, long giaTien, Date ngayBatDau, Date ngayKetThuc) {
        this.maMatBang = maMatBang;
        this.trangThai = trangThai;
        this.dienTich = dienTich;
        this.tang = tang;
        this.loaiMatBang = loaiMatBang;
        this.giaTien = giaTien;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    // Getter và Setter
    public String getMaMatBang() {
        return maMatBang;
    }

    public void setMaMatBang(String maMatBang) {
        this.maMatBang = maMatBang;
    }

    public TrangThai getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThai trangThai) {
        this.trangThai = trangThai;
    }

    public double getDienTich() {
        return dienTich;
    }

    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }

    public int getTang() {
        return tang;
    }

    public void setTang(int tang) {
        this.tang = tang;
    }

    public LoaiMatBang getLoaiMatBang() {
        return loaiMatBang;
    }

    public void setLoaiMatBang(LoaiMatBang loaiMatBang) {
        this.loaiMatBang = loaiMatBang;
    }

    public long getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(long giaTien) {
        this.giaTien = giaTien;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    @Override
    public String toString() {
        return "MatBang{" +
                "maMatBang='" + maMatBang + '\'' +
                ", trangThai=" + trangThai.getValue() +
                ", dienTich=" + dienTich +
                ", tang=" + tang +
                ", loaiMatBang=" + loaiMatBang.getValue() +
                ", giaTien=" + giaTien +
                ", ngayBatDau=" + ngayBatDau +
                ", ngayKetThuc=" + ngayKetThuc +
                '}';
    }
}