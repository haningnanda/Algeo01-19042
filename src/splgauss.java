import java.util.Scanner;

public class splgauss {
    public void splgauss_main(){
        int n;
        int m;
        Scanner prog = new Scanner(System.in);
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
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                System.out.printf("%f ", spl[i][j]);
            }
            System.out.println("\n");
        }
    }
}
