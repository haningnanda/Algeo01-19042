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
        System.out.print("Masukkan jumlah kolom : ");
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

	public void Gauss(){
		/* MATRIKS ESELON BARIS TEREDUKSI */
		int i, j, k, move = 1, mark = 0, total = 0;
		boolean found = false;
		float temp;

		for (i = 0; i < m; i++){
			if (matriks[i][i] != 0){
				continue;
			}
			else if (matriks[i][i] == 0){ //matriks[i][i] == 0
				while (matriks[i + move][i] == 0 && (i + move) < m){
					move++;
					for (j = i; j < m; j++){
						temp = matriks[i][j];
						matriks[i][j] = matriks[i + move][j];
						matriks[i + move][j] = temp;
					}
				}
			}
			for (j = 0; j < n; i++){
				if (i == j){
					continue;
				}
				else{ // i != j
					matriks[j][i] /= matriks[i][i];
					for (k = 0; k < m; k++){
						matriks[j][k] -= (matriks[j][i] * matriks[i][k]);
					}
				}
			}
		}

		for (i = 0; i < m; i++){
			for (j = 0; j < n; j++){
				total += matriks[i][j];
			}
			if (total == matriks[i][j]){
				mark = 2;
			}
			
		}

		System.out.println(" ");
		System.out.println("Hasil Eliminasi Gauss Jordan : ");
		printMatriks();
		for (i = 0; i < m; i++){
			for (j = 0; j < n; j++){
				System.out.print(matriks[i][j] + " ");
            }
            System.out.println("");
        }

		if (mark == 2){
			System.out.println("Solusi sangat banyak");
		}
		else{
			System.out.println(" ");
			System.out.println("Solusi : ");
			for (i = 0; i < m; i++){
				System.out.print("X" + (i + 1) + " : ");
				System.out.println(matriks[i][m] / matriks[i][i] + " ");
			}
		}
	}

	public void eGaussJordan(){
		/* ELIMINASI GAUSS JORDAN */
		int i, j, k;
		// Memanggil fungsi Eliminasi Gauss dan mengubahnya menjadi Gauss Jordan
		//Gauss();

		for (i = 0; i < m; i++){
			for (j = 0; j < n - 1; j++){
				if (i == j){
					continue;
				}
				else if (i != j){
					if (matriks[i][j] == 0){
						continue;
					}
					else{ //matriks[i][j] != 0
						for (k = i; k <= n; k++){
							matriks[k][j] = matriks[k][j] - (matriks[k][j]*matriks[k+1][j]);
						}
					}
				}
			}
		}
		System.out.println(" ");
		System.out.println("Matriks hasil Gauss Jordan : ");
		printMatriks();
	}

	/*
	public void test(){
		for (i = m-1; i >= 0; i--){
			if ()
			for (j = n-1; j >= 0; j--){
			}
		}
	}


	public void test2(){
		int i, j, k, p = 1;
		int total = 0, mark = 0;
		boolean found = false;
		float temp, q;

		for (i = 0; i < m; i++){
			if (matriks[i][i] != 0){
				continue;
			}
			else{
				while ((i + p) < m && matriks[i + p][i] == 0){
					p++;
					if ((i + p == n) && false){
						mark = 1;
						found = true;
					}
					for (j = i, k = 0; k < m; k++){
						temp = matriks[j][k];
						matriks[j][k] = matriks[j+p][k];
						matriks[j+p][k] = temp;
					}
				}
			}
			for (j = 0; j < n - 1; j++){
				if (i != j){
					q = matriks[j][i] / matriks[i][i];
					for (k = 0; k < m; k++){
						matriks[j][k] -= (matriks[i][k] * q);
					}
				}
			}
		}
	}


	public void test2(){
		int i, j, k, p = 1;
		int total = 0, mark = 0;
		boolean found = false;
		float temp, q;

		for (i = 0; i < m; i++){
			if (matriks[i][i] != 0){
				continue;
			}
			else if (matriks[i][i] == 0){
				while (matriks[i][j] = )
			}
			for (j = 0; j < n; j++){
				for (k = 0; k < m; k ++){

				}
			}
		}
	} */


	public void readInterpolasi(){
		int n; // n(x,y)
		int x; // nilai yang akan ditaksir
		int i, j;
		Scanner in = new Scanner(System.in);

		System.out.print("Masukkan nilai n : ");
		n = in.nextInt();
		this.n = n + 1;

		for (i = 0; i <= n + 1; i++){
			System.out.println("Masukkan x dan y");
			for (j = 0; j <= n + 1; j++){
				System.out.println("Halo");
			}
		}
	}

	public void interpolasiSolve(){
	}

	public void dReduksiBaris(){
		// rumus : determinan = (-1)^swap * matriks diagonal
		int i, j, swap = 1;
		boolean found = false;

		for (i = 0; i < n; i++){
			for (j = 0; j < n; j++){
				if (matriks[j][i] != 0){
					matriks[0][i] = matriks[i][i];
					matriks[i][i] = matriks[i + 1][i];
					matriks[i + 1][i] = matriks[0][i];

					swap *= -1;
					break;
				}
			}

			swap *= matriks[i][i];
			for (i = 0; i < n; i++){
				matriks[i][i] *= (1/matriks[i][i]);
			}

			for (j = 0; j < n; j++){
				for (i = 0; i < n; i++){
					matriks[i + 1][i] += (matriks[i][i] * (-1 * matriks[i + 1][i] / matriks[i][i])); 
				}
			}
		}

		for (i = 0; i < n; i++){
			swap *= matriks[i][i];
		}
		System.out.print("Hasil Determinan : " + swap);
	}

	public void det(){
		// rumus : determinan = (-1)^swap * matriks diagonal
		int i, j, k, swap = 1;
		boolean found = false;

		for (i = 0; i < n; i++){
			for (j = 0; j < n; j++){
				if (matriks[j][i] != 0){
					matriks[j + 1][i] -= (matriks[j+1][i]*matriks[j][i]);
				}
			}
			swap *= matriks[i][i];
			/*
			swap *= matriks[i][i];
			for (i = 0; i < n; i++){
				matriks[i][i] *= (1/matriks[i][i]);
			}

			for (j = 0; j < n; j++){
				for (i = 0; i < n; i++){
					matriks[i + 1][i] += (matriks[i][i] * (-1 * matriks[i + 1][i] / matriks[i][i])); 
				}
			}
			*/
		}
		
		for (i = 0; i < n; i++){
			for (j = 0; j < n; j++){
				System.out.print(matriks[i][j] + " ");
			}
			System.out.println(" ");
		}
		System.out.print("Hasil Determinan : " + swap);
	}
	
	public void add(int a, int b, float x){
		for (int k=0;k<n;k++) {
            matriks[a][k] += matriks[b][k] * x;
        }
	}
	public void swap(int a, int b){
		for (int i=0;i<n;i++) {
			matriks[0][i] = matriks[a][i];
			matriks[a][i] = matriks[b][i];
			matriks[b][i] = matriks[0][i];
		}
	}

	public void coba(){
		int i, j, swap = 1;
		float temp;
		boolean found = false;

		for (i = 0; i < n; i++){
			if (matriks[i][i] == 0){
				for (j = 0; j < (n-1); j++){
					if (matriks[j][i] != 0){
						for (i = 0; i < n ; i++) {
							temp = matriks[i][i];
							matriks[i][i] = matriks[i+1][i];
							matriks[i+1][i] = temp;
						}		
						swap *= -1;
						break;
					}
				}
			}
			swap *= matriks[i][i];
			for (i = 0; i < n; i++){
				matriks[i][i] *= (1/matriks[i][i]);
				for (j = 0; j < n; j++){
					add((i+1), i, -1 * matriks[j][i] / matriks[i][i]);
					//for (i = 0; i < n; i++){
					//	matriks[j][i] += (matriks[i][i] * (-1 * matriks[j][i] / matriks[i][i])); 
					//}
				}
			}
		}

		for (i = 0; i < n; i++){
			swap *= matriks[i][i];
		}

		for (i = 0; i < n; i++){
			for (j = 0; j < n; j++){
				System.out.print(matriks[i][j] + " ");
			}
			System.out.println(" ");
		}
		System.out.print("Hasil Determinan : " + swap);
	}

	public int test(){
		int i, j, k, a = 0, b= 0, hitung = 0;
		float temp;
		float[][] mhsl = new float[300][300];
		float det = 0;
		boolean found = true;

		if (n == 2){
			det = matriks[0][0]*matriks[1][1] - matriks[1][0]*matriks[0][1];
		}
		else{
			for (i = 0; i < n; i++){
				for (j = 0; j < n; j++){
					for (k = 0; k < n; k++){
						if (k == i){
							continue;
						}
						mhsl[a][b] = matriks[j][k];
						b++;
					}
					a++;
				}
				hitung += i;
			}
		}
		for (a = 0; a < n; a++){
			for (b = 0; b < n; b++){
				det += (Math.pow(-1, hitung) * mhsl[a][a]);
			}
		}
		System.out.println("Hasil " + det);
		return 0;
	}

}