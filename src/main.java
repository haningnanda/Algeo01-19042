import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {
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
        if (pilihan==1) {
            System.out.println("Pilih Metode yang Ingin Anda Gunakan :");
            System.out.println("1. Metode eliminasi Gauss\n2. Metode eliminasi Gauss-Jordan\n3. Metode matriks balikan\n4. Kaidah Cramer");
            int pilihan1;
            System.out.println("Pilih Metode :");
            pilihan1 = menu.nextInt();
            if (pilihan1==1) {
                Program menu_1_1= new Program();
                menu_1_1.splgauss();
            }
        }
    } 
}
class Program{
    public void splgauss(){
        int matriks;
        Scanner menprog = new Scanner (System.in);
        System.out.println("Masukkan ukuran matriks : ");
        matriks = menprog.nextInt();
    }
}
