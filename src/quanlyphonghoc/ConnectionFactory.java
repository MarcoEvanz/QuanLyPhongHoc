/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlyphonghoc;

/**
 *
 * @author Admin
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author trung
 */

public class ConnectionFactory {
    public static Connection getConnection(){
        Connection c = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/doanjava?useUnicode=true&characterEncoding=utf-8","root","");
            
        }catch(ClassNotFoundException e){
            System.out.println("ClassNotFoundException " + e);
        }catch (SQLException e){
            System.out.println("SQLException " + e);
        }
        return c;
    }
}

