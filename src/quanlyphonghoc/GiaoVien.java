/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyphonghoc;

/**
 *
 * @author Admin
 */
import java.util.Date;
public class GiaoVien {
    private String MaGV, TenGv,DiaChi,SDT,Username,Password;
    private int sonam;
    private Date ngaysinh;

    public GiaoVien() {
    }

    public GiaoVien(String MaGV, String TenGv, String DiaChi, String SDT, String Username, String Password, Date ngaysinh) {
        this.MaGV = MaGV;
        this.TenGv = TenGv;
        this.DiaChi = DiaChi;
        this.SDT = SDT;
        this.Username = Username;
        this.Password = Password;
        this.ngaysinh = ngaysinh;
    }

    public String getMaGV() {
        return MaGV;
    }

    public void setMaGV(String MaGV) {
        this.MaGV = MaGV;
    }

    public String getTenGv() {
        return TenGv;
    }

    public void setTenGv(String TenGv) {
        this.TenGv = TenGv;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getSonam() {
        return sonam;
    }

    public void setSonam(int sonam) {
        this.sonam = sonam;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
    
    
}
