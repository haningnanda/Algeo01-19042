import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Balikan {

    int i,j,k;
    public int BrsMax = 100;
    public int KolMax = 100;
    public float[][] M1;


    public float[][] inp(){
        Scanner inputUser = new Scanner(System.in);

        System.out.print("Masukkan baris: ");
        this.BrsMax = inputUser.nextInt();
        System.out.print("Masukkan kolom: ");
        this.KolMax = inputUser.nextInt();
        float[][] matriks = new float[BrsMax][KolMax];

        for (i = 0; i < BrsMax; i++) {
            for (j = 0; j < KolMax; j++) {
                matriks[i][j] = inputUser.nextFloat();
            }
        }
        return matriks;
    }

    public void print(float[][] M){
        for (i = 0; i < M.length; i++) {
            for (j = 0; j < M[0].length; j++) {
                System.out.printf("x%d = %.1f ",i+1, M[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public float[][] aij(float[][] M){
        float[][] matriks = new float[M.length][M[0].length - 1];
        for (i = 0; i < M.length; i++) {
            for (j = 0; j < M[0].length - 1; j++) {
                matriks[i][j] = M[i][j];
            }
        }
        return matriks;
    }
    public float[][] bij(float[][] M){
        float[][] matriks = new float[M.length][1];
        for (i = 0; i < M.length; i++) {
            matriks[i][0] = M[i][M[0].length - 1];
        }
        return matriks;
    }
    public float[][] kali(float[][] M1, float[][] M2){
        float[][] hasil = new float[M1.length][M2[0].length];
        for(i = 0; i < M1.length; i++){
            for(j = 0; j < M1.length;j++){
                hasil[i][0] = 0;
                for(k = 0; k < M1.length;k++){
                    hasil[i][0] += M1[i][k]*M2[k][0];
                }
            }
        }
        print(hasil);
        return hasil;
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

    public void save(float[][] x){
        String n = "SPLBalikan.txt";
        try{
            FileWriter fileWriter = new FileWriter(n);
            PrintWriter print = new PrintWriter(fileWriter);

            for (i = 0; i < x.length; i++) {
                for (j = 0; j < x[0].length; j++) {
                    print.printf("%.1f ", x[i][j]);
                }
                print.println();
            }
            print.close();

        } catch (IOException e){
            System.out.println("Terjadi kesalahan " + e.getMessage());
        }
    }

    public void run(){
        Scanner pilih = new Scanner(System.in);
        System.out.print("1. Input dari keyboard\n2. Input dari file\nPilih inputan: ");
        int m = pilih.nextInt();
        if(m == 1) {
            splbalikan(inp());
            System.out.println();
        }else if(m == 2){
            inputfile();
            splbalikan(M1);
            System.out.println();
        }
    }


    public void splbalikan(float[][] M){
        Determinan det = new Determinan();
        Invers inv = new Invers();
        if(aij(M).length == aij(M)[0].length){
            if(det.det(aij(M)) != 0){
                save(kali(inv.invers(aij(M)), bij(M)));
            }
            else{
                System.out.println("Matriks balikan tidak ada, memiliki banyak solusi");
            }
        }else {
            System.out.println("Matriks aij tidak simetris");
        }
    }
}
