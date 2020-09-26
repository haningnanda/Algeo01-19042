import java.util.Scanner;



public class splgauss {

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
    

    void Pengurangan(float [][] spl1, float [][] spl,  int m, int n, int hitung[]){
        for(int j=n-1; j>=0; j--){
            for(int k = j-1; k>=0; k--){
                if(hitung[j]==hitung[k]){
                    for(int a=0; a<m; a++){
                        spl1[j][a] -= spl[k][a];
                    }
                }
            }
        }
    }

    void Pengurutan(float [][] spl, int[] hitung, int n){
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

                            for(int k =0; k<n; k++){
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

    public void splgauss_main(){
        int n;
        int m;
        Scanner prog = new Scanner(System.in);
        System.out.println("Masukkan jumlah baris : ");
        n = prog.nextInt();
        System.out.println("Masukkan jumlah kolom : ");
        m = prog.nextInt();
        float[][] spl = new float [n][m];
        float[][] spl1 = new float [n][m];
        int [] hitung = new int [n];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                spl[i][j]=prog.nextFloat();
            }
        }
        for(int a=0; a<n; a++){

            hitungbaris(hitung, n, m, spl);
            Pengurutan(spl, hitung, n);
            Bagidepan(spl, spl1, n, m);
            Pengurangan(spl1, spl, m, n, hitung);
            for(int i = 0; i<n; i++){
                for(int j = 0; j<m; j++){
                    spl[i][j]=spl1[i][j];
                }
            }
            
        }
        
        
        for(int i = 0; i<n; i++){
            for( int j = 0; j<m; j++){
                System.out.printf("%f ", spl1[i][j]);
            }
            System.out.println("\n");
        }  

    }
}
