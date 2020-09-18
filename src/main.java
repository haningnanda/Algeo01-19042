import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        int i = 0;
        while (i==0){
        System.out.println("MENU");
        System.out.println("Pilih Program yang Ingin Anda Jalankan:");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Regresi Linier Berganda");
        System.out.println("6. Keluar");
        Scanner menu = new Scanner(System.in);
        int pilihan;
        System.out.println("Pilih Menu : ");
        pilihan = menu.nextInt();
        while (pilihan<1 || pilihan>6) {
            System.out.println("Pilih Menu : ");
            pilihan = menu.nextInt();
        }
        if (pilihan==1) {
            System.out.println("Pilih Metode yang Ingin Anda Gunakan :");
            System.out.println("1. Metode eliminasi Gauss\n2. Metode eliminasi Gauss-Jordan\n3. Metode matriks balikan\n4. Kaidah Cramer\n5. Kembali");
            int pilihan1;
            System.out.println("Pilih Metode :");
            pilihan1 = menu.nextInt();
            if (pilihan1==1) {
                Program menu_1_1= new Program();
                menu_1_1.splgauss();
                System.out.println("Apakah Anda ingin melakukan Operasi Lagi?\n1. Ya\n2. Tidak");
                int pilihan1_1;1
                System.out.println("Pilihan :");
                pilihan1_1 = menu.nextInt();
                if (pilihan1_1==1){
                    i=0;
                } else{i=1;}
            }
            else if (pilihan1==5){
                i = 0;
            }
        }
        else{
            i = 1;
        }
    }
    } 
}
class Program{
    public void splgauss(){
        int baris;
        int kolom;
        Scanner menprog = new Scanner (System.in);
        System.out.println("Masukkan jumlah baris : ");
        baris = menprog.nextInt();
        System.out.println("Masukkan jumlah kolom : ");
        kolom = menprog.nextInt();
    }
}
