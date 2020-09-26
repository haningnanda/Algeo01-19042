import java.util.*;
import java.io.*;
import java.lang.*;

public class main {
	public static void main(String[] args) {
		matriks m = new matriks();
		terakhir coba = new terakhir();
		splgauss spl = new splgauss();
		int pilihan;
		Scanner in = new Scanner(System.in);
		

		System.out.println("pilihan Masukkan : \n 1. Input User");
		System.out.print("Masukkan pilihan : ");
		pilihan = in.nextInt();

		if (pilihan == 1){
			//spl.splgauss_main();
			coba.Determinan1_main();
		}
	}
}