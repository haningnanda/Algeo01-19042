import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

public class Splgauss {
    float [][] M1;
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
    void Pengurangan(float [][] spl,  int m, int n, int hitung[]){
        for(int i=n-1; i>0; i--){
                if(hitung[i]==hitung[i-1]&&hitung[i]!=m-1){
                    float faktor = spl[i][hitung[i]]/spl[i-1][hitung[i]];
                    for(int a = hitung[i]; a<m; a++){
                        spl[i][a] = spl[i][a]-(spl[i-1][a]*faktor);
                    }  
                }   
        }
    }
    void Pengurutan(float [][] spl, int[] hitung, int n, int m){
        int j;
        boolean flag = true;   
        int temp;   
        float temp1;
        while ( flag )
        {
                flag= false;    
                for( j=0;  j < n-1;  j++ )
                {
                    if ( hitung[ j ] > hitung[j+1] )   
                    {
                            temp = hitung[ j ];                
                            hitung[ j ] = hitung[ j+1 ];
                            hitung[ j+1 ] = temp;

                            for(int k =0; k<m; k++){
                                temp1=spl[j][k];
                                spl[j][k]=spl[j+1][k];
                                spl[j+1][k]=temp1;
                            }
                            flag = true;              
                    }
                }
        }
    }

    void Bagidepan(float[][] spl, float[][] spl1,int n, int m){
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(spl[i][0]!=0){
                    spl1[i][j] = spl[i][j]/spl[i][0];
                }
                else{
                    int k = 1;
                    while (k<m){
                        if (spl[i][k]!=0){
                            spl1[i][j] = spl[i][j]/spl[i][k];
                            if(spl1[i][j]==-0){
                                spl1[i][j]=0;
                            }
                            k=m;
                        }
                        else{
                            k++;
                        }
                    }
                }
            }
        }
    }

    void hitungbaris(int [] hitung, int n, int m, float[][] spl){
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                hitung[i] = hitbrs(spl, n, m, i);
            }
        }
    }
    void prin(float [][] a, int n, int m){
        int i, j, k, x;
        float temp;
        for (i = 1; i < n; i++) {
            j = 0;
            while (j < m-1 && a[i][j] == 0 ){
                    j++;
            }
            if (j < m-1 ){
                for (k = i-1; k >= 0; k--){
                    temp = a[k][j];
                    for (x = j; x < m; x++){
                            a[k][x] = a[k][x] - (temp*a[i][x]);
                    }
                }
            }
        }
        for (i =0; i < n; i++) {
            j = 0;
            while (j < m-1 && a[i][j] == 0){
                j++;
            }
            if (j < m-1){
                System.out.printf("%c = %f", (j+65), a[i][m-1]);
                for (k = j+1; k < m-1; k++){
                    if (a[i][k] > 0){
                        System.out.printf(" - %f%c", a[i][k], (k+65));
                    } else if (a[i][k] < 0){
                        System.out.printf(" + %f%c", a[i][k], (k+65));
                    }
                }
                System.out.println("\n");
            }
        }
    }

    void notparam (float[][] matsumcol, int n, float[] eks){
        for(int i = n-1; i>=0; i--){
            eks[i] = matsumcol[i][n];
            for(int j = i+1; j<n; j++){
                eks[i] -= matsumcol[i][j] * eks[j];
            }
            eks[i]= eks[i]/matsumcol[i][i];
        }
    }

    public void splgauss_txt(){
        inputfile();
        int m= M1[0].length;
        int n = M1.length;
        int [] hitung = new int[m];
        for(int i =0; i<n; i++){
            hitungbaris(hitung, n, m, M1);
             Pengurutan(M1, hitung, n, m);
             Pengurangan(M1, m, n, hitung);
         }
         for(int i = 0; i<n; i++){
             hitungbaris(hitung, n, m, M1);
         }
         for(int i = 0; i<n; i++){
             Pengurutan(M1, hitung, n, m);
         }
         float [][] spl1 = new float[n][m];
         for(int i = 0; i<n; i++){
             Bagidepan(M1, spl1, n, m);
         }
         prin(spl1, n, m);
    }
    

    public void gauss() {
        Scanner prog = new Scanner(System.in);
        int n;
        int m;
        System.out.println("Masukkan jumlah baris : ");
        n = prog.nextInt();
        System.out.println("Masukkan jumlah kolom : ");
        m = prog.nextInt();
        float[][] spl = new float [n][m];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                spl[i][j]=prog.nextFloat();
            }
        }
        int [] hitung = new int [n];
        for(int i =0; i<n; i++){
           hitungbaris(hitung, n, m, spl);
            Pengurutan(spl, hitung, n, m);
            Pengurangan(spl, m, n, hitung);
        }
        for(int i = 0; i<n; i++){
            hitungbaris(hitung, n, m, spl);
        }
        for(int i = 0; i<n; i++){
            Pengurutan(spl, hitung, n, m);
        }
        float [][] spl1 = new float[n][m];
        for(int i = 0; i<n; i++){
            Bagidepan(spl, spl1, n, m);
        }
        prin(spl1, n, m);
    }
}
