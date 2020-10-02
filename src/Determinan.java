import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Determinan {
    public float deter = 0;
    public float[][] M1;
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

        return matriks;
    }
    public void save(float x){
        String n = "Determinan.txt";
        try{
            FileWriter fileWriter = new FileWriter(n);
            PrintWriter print = new PrintWriter(fileWriter);
            print.print(x);
            print.close();

        } catch (IOException e){
            System.out.println("Terjadi kesalahan " + e.getMessage());
        }
    }

    public void inputfile(){
        Scanner nama = new Scanner(System.in);
        System.out.print("Masukkan nama file: ");
        String n = nama.nextLine();
        try{
            File myFile = new File(n);
            Scanner Reader = new Scanner(myFile);

            int i,j;
            int baris=-1;
            int kolom;
            ArrayList<ArrayList<Float>> temp = new ArrayList<ArrayList<Float>>();

            while(Reader.hasNextLine()){
                baris++;
                temp.add(new ArrayList<Float>());
                String brs = Reader.nextLine();
                Scanner Readerbrs = new Scanner(brs);
                while(Readerbrs.hasNextFloat()){
                    float elmt = Readerbrs.nextFloat();
                    temp.get(baris).add(elmt);
                }
            }
            kolom = temp.get(0).size();
            this.M1 = new float[temp.size()][temp.get(0).size()];
            for(i=0;i<=baris;i++){
                for(j=0;j<kolom;j++){
                    this.M1[i][j] = temp.get(i).get(j);
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Terjadi kesalahan " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void run(){
        Scanner pilih = new Scanner(System.in);
        System.out.print("1. Input dari keyboard\n2. Input dari file\nPilih inputan: ");
        int m = pilih.nextInt();
        if(m == 1){
            save(det(inp()));
            System.out.printf("Determinannya adalah: %.1f\n", deter);
            System.out.println();
        }else if(m == 2){
            inputfile();
            if(M1.length == M1[0].length){
                save(det(M1));
                System.out.printf("Determinannya adalah: %.1f\n", deter);
                System.out.println();
            }else{
                System.out.println("Matriks tidak simetris");
                System.out.println();
            }
        }
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
            this.deter = dtr;
            return dtr;
        }
    }
}