package model;

public class Food {
    private String maMonAn, tenMonAn;
    private double giaTien;
    private String hinhAnh, trangThai;

    public Food(String maMonAn, String tenMonAn, double giaTien, String hinhAnh, String trangThai) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.giaTien = giaTien;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
    }

    public Food(String tenMonAn, double giaTien, String hinhAnh, String trangThai) {
        this.tenMonAn = tenMonAn;
        this.giaTien = giaTien;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
    }
    
    
    public String getMaMonAn() {
        return maMonAn;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setMaMonAn(String maMonAn) {
        this.maMonAn = maMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
