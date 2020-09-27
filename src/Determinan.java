package com.tubes;


import java.util.Scanner;

public class Determinan {
    public float[][] inp() {
        Scanner inputUser = new Scanner(System.in);
        int n, i, j;

        // Menerima input user yaitu N, yang artinya akan dibuat matriks ukuran NxN
        System.out.print("Masukkan N (yaitu ukuran matriks dengan NxN): ");
        n = inputUser.nextInt();

        // memberi nilai pada setiap indeks matriks i dan j
        float[][] matriks = new float[n][n];
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                matriks[i][j] = inputUser.nextFloat();
            }
        }

//        for (i = 0; i < n; i++) {
//            for (j = 0; j < n; j++) {
//                System.out.printf("%.1f ", matriks[i][j]);
//            }
//            System.out.println();
//        }
        return matriks;
    }

    public float det(float [][] m){
        int i,j;
        float dtr = 0;

        //ini basisnya
        if (m.length == 2) {
            dtr = m[0][0] * m[1][1] - m[0][1] * m[1][0];
            return dtr;
        } else {
            float[][] M = new float[m.length-1][m.length-1]; //tempat untuk nyimpen minor entri
            for (i = 0; i < m.length; i++) {                //loop untuk mencari minor entri M(i,j)
                for (j = 1; j < m.length; j++) {
                    int k = 0;
                    int a = 0;
                    while (k < m.length) {
                        if (k != i) {
                            M[j - 1][a] = m[j][k];
                            a++;
                        }
                        k++;
                    }
                }
                float pangkat = (float) Math.pow(-1, 1 + (i + 1));
                float el = m[0][i]*det(M); // ketika pangkat kali el itu adalah kofaktor entri dari a(i,j) dan det(M) merupakan fungsi rekursif
                dtr = dtr + pangkat*el;
            }
            return dtr;
        }
    }
}