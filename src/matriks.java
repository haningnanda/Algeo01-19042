import java.util.*;
import java.io.*;
import java.lang.*;

public class matriks {

	int m = 0, n = 0;
	float[][] matriks = new float[300][300];


	matriks(){
		int i, j;

		for (i = 0; i <= m; i++){
			for (j = 0; j <= n; j++){
				this.matriks[i][j] = 0;
			}
		}
		this.m = m;
		this.n = n;
	}

	public void readMatriksUser(){
		/* BACA INPUT MATRIKS DARI USER */

		Scanner in = new Scanner(System.in);

        // Scan m dan n
        System.out.print("Masukkan jumlah baris : ");
        m = in.nextInt();

        System.out.print("Masukkan jumlah kolom : ");
        n = in.nextInt();
	}

	public void readMatriksPersegiUser(){
		/* BACA INPUT MATRIKS DARI USER */

		Scanner in = new Scanner(System.in);

        // Scan n
        System.out.print("Masukkan jumlah kolom dan baris : ");
        n = in.nextInt();
	}
	
	public void writeMatriksPersegi(){
		/* MASUKKAN ELEMEN MATRIKS FROM USER*/
		int i, j;
		Scanner in = new Scanner(System.in);

        System.out.println("Masukkan elemen matriks : ");
        for (i = 0; i < n; i++){
            for (j = 0; j < n; j++){
                System.out.print("Masukkan indeks baris ke-" + i + " kolom ke-" + j + " : ");
                matriks[i][j] = in.nextInt();
            }
        }
	}

	public void writeMatriks(){
		/* MASUKKAN ELEMEN MATRIKS FROM USER*/
		int i, j;
		Scanner in = new Scanner(System.in);

        System.out.println("Masukkan elemen matriks : ");
        for (i = 0; i < m; i++){
            for (j = 0; j < n; j++){
                System.out.print("Masukkan indeks baris ke-" + i + " kolom ke-" + j + " : ");
                matriks[i][j] = in.nextInt();
            }
        }
	}

	public void printMatriks(){
		/* PRINT MATRIKS*/
		int i, j;
        System.out.println("");
        System.out.println("Matriks " + m + " x " + n + " yang dibuat : ");
        for (i = 0; i < m; i++){
            for (j = 0; j < n; j++){
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println("");
        }
	}

	public void printMatriksPersegi(){
		/* PRINT MATRIKS*/
		int i, j;
        System.out.println("");
        System.out.println("Matriks " + n + " x " + n + " yang dibuat : ");
        for (i = 0; i < n; i++){
            for (j = 0; j < n; j++){
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println("");
        }
	}

	// Gauss
	public void Gauss(){
		int i, j, c = 0, next = 0;
		boolean found = false;

		for (i = 0; i < m; i++){
			if (matriks[i][i + next] == 0){
				found = false;
				for (j = 0; i < m; i++){
					if (matriks[j][i] != 0){
						for (i = 0; i < n; i++){
							matriks[0][i] = matriks[i][i];
							matriks[i][i] = matriks[j][i];
							matriks[j][i] = matriks[0][i];
						}
						found = true;
						break;
					}
				}
				if (found == false){
					next++;
					i--;
					continue;
				}
			}
			for (i = 0; i < n; i++){
				matriks[i][i] *= (1/matriks[i][i + next]); 
				for (j = i + 1; j < m; j++){
					matriks[j][i] += (matriks[i][i] * (-1 * matriks[j][i + next] / matriks[i][i + next]));
				}
			}
			for (i = 0; i < m; i++){
				for (j = 0; j < n; j++){
					if (matriks[i][j] != 0){
						c = j;
						break;
					}
				}
				if (c == n-1){
					for (i = 0; i < n; i++){
						matriks[i][i] *= (1/matriks[i][c]);
					}
				}
			}
		}

		System.out.println("");
        System.out.println("Matriks " + m + " x " + n + " yang dibuat : ");
        for (i = 0; i < m; i++){
            for (j = 0; j < n; j++){
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println("");
        }
	}

	//Gauss Jordan cara 1


	//GaussJordanFixBgt
	public void eliminasiGaussJordan(){
		int i, j, k;
		int NR = 1; //NextRow
		boolean found = false;
		float det = 0;
		float temp, MB; // matriks baru

		for (i = 0; i < n; i++){
			if (matriks[i][i] == 0){
				while (((i + NR) < n) && (matriks[i + NR][i] == 0) && (found == false)){
					NR++;
					if (((i + NR) == n) && (found = false)){
						found = true;
					}
					else{
						continue;
					}

					for (k = 0; k < n; k++){
						temp = matriks[i][k];
						matriks[i][k] = matriks[i + NR][k];
						matriks[i + NR][k] = temp;
					}
				}
			}
			for (j = 0; j < n; j++){
				if (i != j){
					MB = matriks[j][i] / matriks[i][i];

					for (k = 0; k < n; k++){
						matriks[j][k] -= (matriks[i][k] * MB);
					}
				}
			}
		}
	}

	// Interpolasi

	public void Interpolasi(){
		int n, x, y; // n(x,y)
		int xt; // nilai yang akan ditaksir
		int i, j;
		boolean found = false;
		Scanner in = new Scanner(System.in);

		System.out.print("Masukkan nilai n : ");
		n = in.nextInt();
		System.out.print("Masukkan nilai x taksiran : ");
		xt = in.nextInt();

		
		for (i = 0; i <= n; i++){
			System.out.println("Masukkan (x" + i + ", y" + i + ") : ");
			System.out.print("x" + i + " : ");
			x = in.nextInt();
			System.out.print("y" + i + " : ");
			y = in.nextInt();
			System.out.println(" ");
		}

		// 

		// HASIL INTERPOLASI
		Gauss(); //apa gaus jordan yak
		for (i = 0; i < n; i++){
			if (matriks[i][n] == 0){
				continue;
			}
			else{
				for (j = 0; j < n; j++){
					while (matriks[i][j] != 0){
						found = true;
					}
				}
				if (found == true){
					System.out.println("Tidak ada polinom");
				}
			}
		}

		System.out.println("Polinom yang dihasilkan : ");
		for (i = 0; i < n; i++){
			if (matriks[i][n] < 0){
				if ((i > 0) && (found == false)){
					matriks[i][n] = -matriks[i][n]; 
				}
			}
			//else if (matriks[i][n] == 0){}
			else if (matriks[i][n] > 0){
				continue;
			}
		}

		//System.out.println("Hasil Interpolasi : ", )
	}

	// Determinan FIX BGT

	public void determinan(){
		int i, j, k;
		int NR = 1; //NextRow
		boolean found = false;
		int swap = 1;
		float temp, MB; // matriks baru

		System.out.println("");
        System.out.println("Matriks setelah di reduksi : ");
		for (i = 0; i < n; i++){
			if (matriks[i][i] == 0){
				for (j = i + 1; j < n; j++){
					if (matriks[j][i] != 0){
						for (k = 0; k < n; k++){
							temp = matriks[i][k];
							matriks[i][k] = matriks[j][k];
							matriks[j][k] = temp;
						}
						swap *= -1;
						break;
					}
				}
				/*
				while (((i + NR) < n) && (matriks[i + NR][i] == 0) && (found == false)){
					NR++;
					if (((i + NR) == n) && (found = false)){
						found = true;
					}
					else{
						continue;
					}

					for (k = 0; k < n; k++){
						temp = matriks[i][k];
						matriks[i][k] = matriks[i + NR][k];
						matriks[i + NR][k] = temp;
					}
					swap *= -1;
				}
				*/
			}
			for (j = 0; j < n; j++){
				if (i != j){
					MB = matriks[j][i] / matriks[i][i];

					for (k = 0; k < n; k++){
						matriks[j][k] -= (matriks[i][k] * MB);						
					}
				System.out.print(matriks[i][j] + " ");
			}
			System.out.println(" ");
		}
        
        System.out.println("");
        for (i = 0; i < n; i++){
			swap *= matriks[i][i];
		}
		System.out.print("Hasil Determinan : " + swap);
	}
}