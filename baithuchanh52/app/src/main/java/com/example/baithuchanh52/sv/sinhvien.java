package com.example.baithuchanh52.sv;

import java.io.Serializable;

public class sinhvien implements Serializable {
    private String hoten;
    private String sodienthoai;
    private String email;

    public sinhvien() {
    }

    public sinhvien(String hoten, String sodienthoai, String email) {
        this.hoten = hoten;
        this.sodienthoai = sodienthoai;
        this.email = email;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return   hoten + '-' +
                 sodienthoai + '-'+ email  ;
    }
}
