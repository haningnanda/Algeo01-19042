import java.util.Scanner;

public class main {
    public static void main(String[] args) {
splgauss splgauss_pro = new splgauss();
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
                if(pilihan1==1){
                    splgauss_pro.splgauss_main();
                }
            }
            else if(pilihan==2){
                System.out.println("Isi disini");
            }
            else if(pilihan==3){
                System.out.println("isi disini");
            }
            else if(pilihan==4){
                System.out.println("isi yuk");
            }
            else if(pilihan==5){
                System.out.println("cek dicoba");
            }
        } 
    }
}