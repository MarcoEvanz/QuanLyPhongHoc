/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import java.util.Scanner;
public class baitap {
    static public String kiemtratamgiac ( int a, int b, int c)
    {
        if (a == b  && a == c)
        {
            return "Tam giac deu";
        }
        else if ( ( a == b && a != c ) || ( b==c && b !=a ) || (a == c && c != b ))
        {
            return "Tam giac can";
        }
        else if (c*c == (a*a + b*b))
        {
            return "Tam giac vuong";
        }
        else return  "Tam giac thuong";
    }
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap canh thu nhat: ");
        int a = scanner.nextInt();
        System.out.print("Nhap canh thu hai: ");
        int b = scanner.nextInt();
        System.out.print("Nhap canh thu ba: ");
        int c = scanner.nextInt();
        System.out.print("Tam giac nay la: " + kiemtratamgiac(a,b,c));
    }
}
