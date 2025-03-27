package model;

import java.time.LocalDate;

public class Employee {
    private String maNhanVien;
    private String password;
    private String vaiTro;
    private String tenNhanVien;
    private LocalDate ngaySinh; 
    private String soDienThoai;

    public Employee(String maNhanVien, String password, String vaiTro, String tenNhanVien, LocalDate ngaySinh, String soDienThoai) {
        this.maNhanVien = maNhanVien;
        this.password = password;
        this.vaiTro = vaiTro;
        this.tenNhanVien = tenNhanVien;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public String getPassword() {
        return password;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }
    
    
}
