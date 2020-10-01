import java.util.Scanner;


public class splcramer {

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
    void tukar(float[][]matriks1, float[]matriks2, int n, int x){
        for(int j=0; j<n; j++){
                    float temp = matriks1[j][x];
                    matriks1[j][x]=matriks2[j];
                    matriks2[j]= temp;
        }
    }
    public void splcramer_main(){
        int n;
        int m;
        Scanner prog = new Scanner(System.in);
        System.out.println("Masukkan n : ");
        n = prog.nextInt();
        m = n+1;
        float matriks [][] = new float [n][m];
        float deter [] = new float [n];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                matriks[i][j]=prog.nextFloat();
            }
        }

        float matriks2 [] = new float[n];
        for(int i = 0; i<n; i++){
            matriks2[i]=matriks[i][n];
        }
        // Determinan matriks yang asli
        float matriks1 [][] = new float [n][n];
        for(int i = 0; i<n; i++){
            for(int j=0; j<n; j++){
                matriks1[i][j]=matriks[i][j];
            }
        }
        float hasil = det(matriks1);
        if (hasil == 0){
            System.out.println("Tidak ada solusi");
        }else{
            float [] hasil1 = new float [n];
            for (int i =0; i<n; i++){
                        tukar(matriks1, matriks2, n, i);
                        hasil1[i]=det(matriks1);
                        tukar(matriks1, matriks2, n, i);
            }
            float pers [] = new float [n];
            for(int i =0; i<n; i++){
                pers[i] = hasil1[i]/hasil;
            }


            for(int i=0; i<n; i++){
                System.out.printf("x%d = %f \n",(i+1), pers[i] );
            }
        }
    }
}
