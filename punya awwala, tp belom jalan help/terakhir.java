import java.util.*;
import java.io.*;
import java.lang.*;

public class terakhir{

	static int hitbrs (float[][]spl, int n, int m, int i){
        int count = 0;
        int k =0;
        while (k<n){
            if (spl[i][k] != 0){
                k=n;
            }
            else{
                count++;
                k++;
            }
        }
        return count;
    }

	public void add(int n, int a, int b, float x){
		float[][] temp = new float[n][n];
		for (int i=0;i<n;i++) {
            matriks[a][i] += matriks[b][i] * x;
        }
	}

	public void Determinan1_main(){
		/* BACA INPUT MATRIKS DARI USER */
		int i, j, swap = 1;
		int n;
		boolean found = false;

        // Scan n
        Scanner in = new Scanner(System.in);
        System.out.print("Masukkan jumlah kolom : ");
        n = in.nextInt();

        float[][] matriks = new float[n][n];
        float[][] temp = new float[n][n];

		for (i = 0; i < n; i++){
			if (matriks[i][i] == 0){
				for (j = 0; j < (n-1); j++){
					if (matriks[j][i] != 0){
						for (i = 0; i < n ; i++) {
							temp[0][i] = matriks[i][i];
							matriks[i][i] = matriks[i+1][i];
							matriks[i+1][i] = temp[0][i];
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
					add(n, (i+1), i, -1 * matriks[j][i] / matriks[i][i]);
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

	public void Determinan2_main(){
		int i, j, k, a = 0, b= 0, swap =1;
		int n;
		float temp;
		float[][] mhsl = new float[300][300];
		float det = 0;
		boolean found = true;

		/* BACA INPUT MATRIKS DARI USER */
		Scanner in = new Scanner(System.in);

        // Scan n
        System.out.print("Masukkan jumlah kolom : ");
        n = in.nextInt();

        float[][] matriks = new float[n][n];


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
				det += (Math.pow(-1, i) * mhsl[a][a]);
			}
		}
		System.out.println("Hasil " + det);
	}

}
