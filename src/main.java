import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Splgauss splgauss_pro = new Splgauss();
        Splcramer splcramer_pro = new Splcramer();
        Regresi reg = new Regresi();
        Balikan splbalikan = new Balikan();
        Determinan det = new Determinan();
        Invers inv = new Invers();
        matriks m = new matriks();
        determinan2 d = new determinan2();
        boolean keluar = false;
        while (!keluar) {
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
            System.out.print("Pilih Menu : ");
            pilihan = menu.nextInt();
            while (pilihan < 1 || pilihan > 6) {
                System.out.print("Pilih Menu : ");
                pilihan = menu.nextInt();
            }
            if (pilihan == 1) {
                System.out.println("Pilih Metode yang Ingin Anda Gunakan :");
                System.out.println("1. Metode eliminasi Gauss\n2. Metode eliminasi Gauss-Jordan\n3. Metode matriks balikan\n4. Kaidah Cramer\n5. Kembali");
                int pilihansub;
                System.out.print("Pilih Metode : ");
                pilihansub = menu.nextInt();
                if (pilihansub == 1) {
                    System.out.print("1. Input dari keyboard\n2. Input dari file\nPilih inputan: ");
                    int pilihaninput = menu.nextInt();
                    if (pilihaninput == 1) {
                        splgauss_pro.gauss();
                    } else if (pilihaninput == 2) {
                        splgauss_pro.splgauss_txt();
                    }
                }else if (pilihansub == 2) { //gaus jordan
                    System.out.print("1. Input dari keyboard\n2. Input dari file\nPilih inputan: ");
                    int pilihaninput = menu.nextInt();
                    if (pilihaninput == 1) {
                        m.readMatriksUser();
                        m.writeMatriks();
                        m.printMatriks();
                        m.solusigaussjordan();
                    } else if (pilihaninput == 2) {
                        m.gaussjordanfile();
                    }
                }else if (pilihansub == 3) {
                    splbalikan.run();
                }else if (pilihansub == 4) {
                    System.out.print("1. Input dari keyboard\n2. Input dari file\nPilih inputan: ");
                    int pilihaninput = menu.nextInt();
                    if(pilihaninput == 1){splcramer_pro.splcramer_main();}
                    else if(pilihaninput == 2){splcramer_pro.splcramer_text();}
                }
            }else if (pilihan == 2){
                System.out.println("1. Determinan kofaktor\n2. determinan reduksi baris\n3. Kembali");
                System.out.print("Pilih metode: ");
                int pilihansub;
                pilihansub = menu.nextInt();
                if(pilihansub == 1){
                    det.run();
                }else if(pilihansub == 2) {
                    System.out.print("Baca : \n 1. Keyboard \n 2. File");
                    System.out.print("\nMasukkan pilihan : ");
                    int pilihaninput = menu.nextInt();
                    if (pilihaninput == 1) {
                        d.readMatriksPersegiUser();
                        d.writeMatriksPersegi();
                        d.printMatriksPersegi();
                        d.determinan();
                    }else if(pilihaninput == 2) {
                        d.determinanfile();
                    }
                }
            }else if (pilihan == 3) {
                inv.run();
            }else if (pilihan == 4) {
                System.out.print("1. Input dari keyboard\n2. Input dari file\nPilih inputan: ");
                int pilihaninput = menu.nextInt();
                if (pilihaninput == 1){
                    m.interpolasi();
                }else if (pilihaninput == 2){
                    m.interpolasi();
                }
            }else if (pilihan == 5) {
                System.out.print("1. Input dari keyboard\n2. Input dari file\nPilih inputan: ");
                int pilihaninput = menu.nextInt();
                if(pilihaninput==1){
                    reg.regresi_main();
                }else if(pilihaninput==2){
                    reg.regresi_text();
                }
            }else if (pilihan == 6) {
                keluar = true;
            }
        }
    }
}