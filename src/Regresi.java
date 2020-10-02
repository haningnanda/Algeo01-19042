import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

public class Regresi {

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

    float sumcol(float[][] matriks, int j, int n){
        float count = 0;
        for(int i=0; i<n; i++){
            count += matriks[i][j];
        }
        return count;
    }
    float kalicol(float [][] matriks, int n, int a, int b){
        int count = 0;
        for(int i = 0; i<n; i++){
            count += matriks[i][a]*matriks[i][b];
        }
        return count;
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
    
    void notparam (float[][] matsumcol, int n, float[] eks){
        for(int i = n-1; i>=0; i--){
            eks[i] = matsumcol[i][n];
            for(int j = i+1; j<n; j++){
                eks[i] -= matsumcol[i][j] * eks[j];
            }
            eks[i]= eks[i]/matsumcol[i][i];
        }
    }

    public void regresi_text(){
        Scanner scan = new Scanner(System.in);
        inputfile();
        int n = M1.length;
        int m = M1[0].length;
        float [] cari = new float [m-1];
        for (int i=0; i<m-1; i++){
            cari[i] = scan.nextFloat();
        }
        float [][] matsumcol = new float[m][m+1];
        matsumcol[0][0] = n;
        for(int j=0; j<m;j++){
            matsumcol[0][j+1] = sumcol(M1, j, n);
        }
        for(int i=1; i<m; i++){
                matsumcol[i][0] = matsumcol[0][i];       
        }
        for(int j=0; j<m-1; j++){
            matsumcol[j+1][m] = kalicol(M1, n, j, m-1);
        }
        for(int j=0; j<m-1;j++){
            for(int k=0; k<m-1; k++){
                    matsumcol[j+1][k+1] = kalicol(M1, n, j, k);           
            }
        }
        for(int j=0; j<m;j++){
            for(int k=0; k<m+1; k++){
                System.out.printf("%f ", matsumcol[j][k]);
            }
            System.out.println("\n");
        }
        int [] hitung = new int [m];
        for(int i =0; i<n; i++){
            hitungbaris(hitung, m, m+1, matsumcol);
             Pengurutan(matsumcol, hitung, m, m+1);
             Pengurangan(matsumcol, m+1, m, hitung);
         }
         for(int i = 0; i<n; i++){
             hitungbaris(hitung, m, m+1, matsumcol);
         }
         for(int i = 0; i<n; i++){
             Pengurutan(matsumcol, hitung, m, m+1);
         }
         float [][] mat = new float[m][m+1];
         for(int i = 0; i<n; i++){
             Bagidepan(matsumcol, mat, m, m+1);
         }
         float[] eks = new float [m];
         notparam(matsumcol, m, eks);
         for(int i =0; i<m; i++){
         }
        float count =eks[0];
        for (int i=0; i<m-1; i++){
            count = count + (eks[i+1]*cari[i]);
        }
        System.out.printf("%f", count);
    }

    public void regresi_main(){
        Scanner scan = new Scanner(System.in);
        int n, m;
        n = scan.nextInt();
        m = scan.nextInt();
        float [][] matriks = new float [n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                matriks[i][j]=scan.nextInt();
            }
        }
        float [] cari = new float [m-1];
        for (int i=0; i<m-1; i++){
            cari[i] = scan.nextFloat();
        }

        float [][] matsumcol = new float[m][m+1];
        matsumcol[0][0] = n;
        for(int j=0; j<m;j++){
            matsumcol[0][j+1] = sumcol(matriks, j, n);
        }
        
        for(int i=1; i<m; i++){
                matsumcol[i][0] = matsumcol[0][i];
                
        }

        for(int j=0; j<m-1; j++){
            matsumcol[j+1][m] = kalicol(matriks, n, j, m-1);
        }
        
        for(int j=0; j<m-1;j++){
            for(int k=0; k<m-1; k++){
                    matsumcol[j+1][k+1] = kalicol(matriks, n, j, k);           
            }
        }

        for(int j=0; j<m;j++){
            for(int k=0; k<m+1; k++){
                System.out.printf("%f ", matsumcol[j][k]);
            }
            System.out.println("\n");
        }

        int [] hitung = new int [m];
        for(int i =0; i<n; i++){
            hitungbaris(hitung, m, m+1, matsumcol);
             Pengurutan(matsumcol, hitung, m, m+1);
             Pengurangan(matsumcol, m+1, m, hitung);
         }
 
         for(int i = 0; i<n; i++){
             hitungbaris(hitung, m, m+1, matsumcol);
         }
 
         for(int i = 0; i<n; i++){
             Pengurutan(matsumcol, hitung, m, m+1);
         }
 
         float [][] mat = new float[m][m+1];
         for(int i = 0; i<n; i++){
             Bagidepan(matsumcol, mat, m, m+1);
         }

         float[] eks = new float [m];
         notparam(matsumcol, m, eks);
         for(int i =0; i<m; i++){
         }

        float count =eks[0];
        for (int i=0; i<m-1; i++){
            count = count + (eks[i+1]*cari[i]);
        }
        System.out.printf("%f \n", count);
    }
}
