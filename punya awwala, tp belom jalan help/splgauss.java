import java.util.Scanner;

public class splgauss {
    public void splgauss_main(){
        int n;
        int m;
        boolean tkr = true;
        Scanner prog = new Scanner(System.in);
        System.out.println("Masukkan jumlah baris : ");
        n = prog.nextInt();
        System.out.println("Masukkan jumlah kolom : ");
        m = prog.nextInt();
        float[][] spl = new float [n][m];
        float[][] tukar = new float [n][m];
        float[][] spl1 = new float [n][m];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                spl[i][j]=prog.nextFloat();
            }
        }

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(spl[0][0]==0){
                    if(spl[i][0]!=0){
                        for( j = 0; j<m; j++){
                            tukar[0][j]=spl[0][j];
                            spl[0][j]=spl[i][j];
                            spl[i][j]=tukar[0][j];
                        }
                    }    
                }
            }
        }


        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                spl1[i][j]=spl[i][j];
            }
        }

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
                            k=m;
                        }
                        else{
                            k++;
                        }
                    }
 
                }
            }
        }
        
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                System.out.printf("%f ", spl1[i][j]);
            }
            System.out.println("\n");
        }      
    }
}