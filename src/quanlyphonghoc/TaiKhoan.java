/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyphonghoc;

/**
 *
 * @author Admin
 */
public class TaiKhoan {
    private String MaUser, Username, Password, LaoiTK;

    public TaiKhoan() {
    }

    public TaiKhoan(String MaUser, String Username, String Password) {
        this.MaUser = MaUser;
        this.Username = Username;
        this.Password = Password;
    }

    public String getMaUser() {
        return MaUser;
    }

    public void setMaUser(String MaUser) {
        this.MaUser = MaUser;
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

    public String getLaoiTK() {
        return LaoiTK;
    }

    public void setLaoiTK(String LaoiTK) {
        this.LaoiTK = LaoiTK;
    }
    
}
