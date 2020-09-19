package model;

import java.io.Serializable;

public class sinhVien implements Serializable {
    private String id;
    private String MSSV;
    private String HoTen;
    private String email;
    private String soDienThoai;

    public sinhVien(String MSSV, String hoTen, String email, String soDienThoai) {
        this.MSSV = MSSV;
        HoTen = hoTen;
        this.email = email;
        this.soDienThoai = soDienThoai;
    }

    public sinhVien() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    @Override
    public String toString() {
        return "sinhVien{" +
                "id='" + id + '\'' +
                ", MSSV='" + MSSV + '\'' +
                ", HoTen='" + HoTen + '\'' +
                ", email='" + email + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                '}';
    }


}
