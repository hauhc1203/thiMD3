package model;

public class PhongBan {
    private  int  idP;
    private  String tenphong;


    public PhongBan(int idP, String tenphong) {
        this.idP = idP;
        this.tenphong = tenphong;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getTenphong() {
        return tenphong;
    }

    public void setTenphong(String tenphong) {
        this.tenphong = tenphong;
    }
}
