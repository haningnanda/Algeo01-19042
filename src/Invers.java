import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Invers {
    int i,j,k;
    public int Baris = 0;
    public int Kolom = 0;
    public float[][] M1;

    // fungsi input dari pengguna
    public void inp(){
        Scanner inputUser = new Scanner(System.in);
        int n;
        System.out.print("Masukkan nilai N: ");
        n = inputUser.nextInt();

        this.M1 = new float[n][n];
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                this.M1[i][j] = inputUser.nextFloat();
            }
        }
    }

    // print matriks
    public void print(float[][] M){
        for (i = 0; i < M.length; i++) {
            for (j = 0; j < M.length; j++) {
                System.out.printf("%.1f ", M[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    public void swap(float[][] MATRIKS,float[][] identitas, int index){
        float[] temp = new float[MATRIKS.length];

        boolean bener = false;
        i = index + 1;
        while(i < MATRIKS.length && !bener){
            if(MATRIKS[i][index] == 0){
                i += 1;
            }else{
                for(k = 0;k < MATRIKS.length; k++){
                    temp[k] = MATRIKS[index][k];
                    MATRIKS[index][k] = MATRIKS[i][k];
                    MATRIKS[i][k] = temp[k];
                }
                for(k = 0;k < MATRIKS.length; k++) {
                    temp[k] = identitas[index][k];
                    identitas[index][k] = identitas[i][k];
                    identitas[i][k] = temp[k];
                }
                bener = true;
            }
        }
    }

    public void save(float[][] x){
        String n = "Invers.txt";
        try{
            FileWriter fileWriter = new FileWriter(n);
            PrintWriter print = new PrintWriter(fileWriter);

            for (i = 0; i < Baris; i++) {
                for (j = 0; j < Kolom; j++) {
                    print.printf("%.1f ", x[i][j]);
                }
                print.println();
            }
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
        Determinan det = new Determinan();
        Scanner pilih = new Scanner(System.in);
        System.out.print("1. Input dari keyboard\n2. Input dari file\nPilih inputan: ");
        int m = pilih.nextInt();
        if(m == 1){
            inp();
            System.out.println("Hasil invers: ");
            if(det.det(M1) != 0){
                print(invers(M1));
            }else{
                System.out.println("Matriks tidak mempunyai balikan");
            }
        }else if(m == 2){
            inputfile();
            if(M1.length == M1[0].length){
                System.out.println("Hasil invers: ");
                print(invers(M1));
            }else{
                System.out.println("Matriks tidak simetris");
            }
        }
    }

    public float[][] invers(float[][] MATRIKS){
        float[][] identitas = new float[MATRIKS.length][MATRIKS.length];
        this.Baris = MATRIKS.length;
        this.Kolom = MATRIKS.length;

        //matriks identitas
        for(i = 0; i < MATRIKS.length; i++){
            for(j = 0; j < MATRIKS.length; j++){
                if(i == j){
                    identitas[i][j] = 1;
                } else{
                    identitas[i][j] = 0;
                }
            }
        }

        //matriks balikan
        /*menjadikan baris pertama dan kolom pertama bernilai 1 dengan membagi
        * denagn indek ke (0,0) */
        Determinan deter = new Determinan();
        if(deter.det(MATRIKS) != 0){

            if(MATRIKS[0][0] == 0){
                swap(MATRIKS,identitas,0);
            }

            for(int i = 0; i < MATRIKS.length; i++) {
                for(int j = 0; j < MATRIKS.length ; j++){
                    MATRIKS[0][j] = MATRIKS[0][j] / MATRIKS[0][0];
                    identitas[0][j] = identitas[0][j] / MATRIKS[0][0];
                }
            }


            //matriks segitiga atas

            int a = 0;
            while(a < MATRIKS.length){
                if(MATRIKS[a][a] == 0){
                    swap(MATRIKS,identitas,a);
                }else{
                    //pembuat diagonal menjadi 1
                    float pembagidiagonal = MATRIKS[a][a];
                    for(j=0; j < MATRIKS.length;j++){
                        MATRIKS[a][j] = MATRIKS[a][j] / pembagidiagonal;
                        identitas[a][j] = identitas[a][j] / pembagidiagonal;
                    }
                    //menjadikan dibawah diagonal 0
                    for(k = a+1; k < MATRIKS.length; k++){
                        float pembagi = MATRIKS[k][a] / MATRIKS[a][a];
                        for(j = 0; j < MATRIKS.length; j++){
                            MATRIKS[k][j] = MATRIKS[k][j] - (pembagi*MATRIKS[a][j]);
                            identitas[k][j] = identitas[k][j] - (pembagi*identitas[a][j]);
                        }
                    }
                    a++;
                }
            }

            //matriks segitiga bawah
            int c = MATRIKS.length - 1;
            while(c >= 0){
                for(int i=0; i < c ;i++){
                    float pembagi = MATRIKS[i][c] / MATRIKS[c][c];
                    for(int j = 0; j < MATRIKS.length;j++){
                        MATRIKS[i][j] = MATRIKS[i][j] - pembagi*MATRIKS[c][j];
                        identitas[i][j] = identitas[i][j] - pembagi*identitas[c][j];
                    }

                }
                c--;
            }
            save(identitas);
        }else{
            System.out.println("matriks balikan tidak ada");
        }
        return identitas;
    }
}
