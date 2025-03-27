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
    
}
