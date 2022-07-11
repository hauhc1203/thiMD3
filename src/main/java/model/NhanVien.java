package model;

import java.sql.Date;
import java.time.LocalDate;

public class NhanVien {
    private int id;
    private String ten;
    private Date ngaySinh;
    private String diaChi;
    private String sdt;
    private String email;
    private PhongBan phongBan;

    public NhanVien() {
    }

    public NhanVien(String ten, LocalDate ngaySinh, String diaChi, String sdt, String email, PhongBan phongBan) {
        this.ten = ten;
        this.ngaySinh = Date.valueOf(ngaySinh);
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.phongBan = phongBan;
    }

    public NhanVien(int id, String ten, Date ngaySinh, String diaChi, String sdt, String email, PhongBan phongBan) {
        this.id = id;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.phongBan = phongBan;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }
}
