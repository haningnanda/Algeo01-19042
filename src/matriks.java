import java.util.*;
import java.io.*;
import java.lang.*;

public class matriks {

	int m = 0, n = 0;
	float[][] matriks = new float[300][300];


	matriks(){
		int i, j;

		for (i = 0; i <= m; i++){
			for (j = 0; j <= n; j++){
				this.matriks[i][j] = 0;
			}
		}
		this.m = m;
		this.n = n;
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
            matriks = new float[temp.size()][temp.get(0).size()];
            for(i=0;i<=baris;i++){
                for(j=0;j<kolom;j++){
                    matriks[i][j] = temp.get(i).get(j);
                }
            }

        } catch (FileNotFoundException e){
            System.out.println("Terjadi kesalahan " + e.getMessage());
            e.printStackTrace();
        }
	}

	public void readInterpolasi(){
        int i, k, x,  j = 0;
        Scanner in = new Scanner(System.in);
        System.out.print("Masukkan derajat : ");
        this.m = in.nextInt();
        this.n = 2;

        for (i = 0; i <= m; i++){
            System.out.println("Masukkan (x" + i + ", y" + i + ") : ");
            for (j = 0; j < 1; j++){
                System.out.print("x" + i + " : ");
                matriks[i][j] = in.nextFloat();
            }
            for (j = 1; j < 2; j++){
                System.out.print("y" + i + " : ");
                matriks[i][j] = in.nextFloat();
                System.out.println();
            }
        }
        float[][] interpolasi = new float[m+1][m+2];
        for (i = 0; i <= m; i++) {
            for (j = 0; j < m+1; j++) {
                float val = matriks[i][0];
                interpolasi[i][j] = (float) Math.pow(val,j);
            }
            interpolasi[i][m+1] = matriks[i][1];
        }

        for (i = 0; i <= m; i++) {
            for (j = 0; j < 2; j++) {
                System.out.printf("%.1f ",matriks[i][j]);
            }
            System.out.println();
        }

        for (i = 0; i <= m; i++) {
            for (j = 0; j <= m+1; j++) {
                this.matriks[i][j] = interpolasi[i][j];
            }
            System.out.println();
        }
        for (i = 0; i <= m; i++) {
            for (j = 0; j <= m+1; j++) {
                System.out.printf("%.1f ",matriks[i][j]);
            }
            System.out.println();
        }

        this.m = m + 1;
        this.n = m + 2;
    }

    public void interpolasi(){
        int i, j = 0, c = 0;
        float hasil = 0, hasilakhir = 0, xt;
        boolean found = false;
        readInterpolasi();
		
		solusigaussjordan();
        Scanner in = new Scanner(System.in);
        System.out.print("Masukkan x: ");
        xt = in.nextFloat();
        
        for (i = 0; i <= m; i++){
       		if (matriks[i][n-2] != 0){
       			hasil = (matriks[i][n-2])*(float)(Math.pow(xt, (i + c)));
       			c++;
     		}   	
     		hasilakhir = hasilakhir + hasil;
        }

        System.out.print("Hasil interpolasi = " + hasilakhir);
    }

	public void readMatriksUser(){
		/* BACA INPUT MATRIKS DARI USER */

		Scanner in = new Scanner(System.in);

        // Scan m dan n
        System.out.println("");
        System.out.print("Masukkan jumlah baris : ");
        m = in.nextInt();

        System.out.print("Masukkan jumlah kolom : ");
        n = in.nextInt();
	}

	public void writeMatriks(){
		/* MASUKKAN ELEMEN MATRIKS FROM USER*/
		int i, j;
		Scanner in = new Scanner(System.in);

		System.out.println("");
        System.out.println("Masukkan elemen matriks : ");
        for (i = 0; i < m; i++){
            for (j = 0; j < n; j++){
                System.out.print("Masukkan indeks baris ke-" + i + " kolom ke-" + j + " : ");
                matriks[i][j] = in.nextInt();
            }
        }
        System.out.println("");
        System.out.println("MATRIKS AWAL : ");
	}

	public void printMatriks(){
		/* PRINT MATRIKS*/
		int i, j;
        for (i = 0; i < m; i++){
            for (j = 0; j < n; j++){
                System.out.print(matriks[i][j] + "	");
            }
            System.out.println("");
        }
	}

    public int Pivot(int i){
        int k = 1;
        boolean found = true;

        while (found & k < n){
            if (matriks[i][k] == 0){
                k += 1;
            } else {
                found = false;
            }
        }
        return k;
    }

    public boolean makeZeroBrs(int i){
        int k = 0;
        boolean found = true;

        while (found & k < n){
            if (matriks[i][k] == 0){
                k += 1;
            } else {
                found = false;
            }
        }
        return found;
    }

    public boolean isNol(int j, int i){
        boolean found;

        found = true;
        if (j >= n){
            found = false;
        } else {
            while (found & i <= m){
                if (matriks[i][j] != 0){
                    found = false;
                } else {
                    i += 1;
                }
            }
        }
        return found;
    }

    public void gaussjordan(){
        int i = 0, j = 0, k, l;
        float temp, MB;
        boolean found = true;

        while ((i <= m) & (j < n)){
            while (isNol(j,i)){
                j += 1;
            }

            k = i;
            while (found & (k <= m)){
            	if (matriks[k][j] != 0){
            		found = false;
            	}
            	else{
            		k += 1;
            	}
            }

            if (j < n){
            	for (l = 0; l < n; l++){
            		temp = matriks[k][l];
            		matriks[k][l] = matriks[i][l];
            		matriks[i][l] = temp;
            	}

            	MB = matriks[i][j];
            	for (l = 0; l < n; l++){
            		matriks[i][l] /= MB;
            	}

            	k = i+1;
            	while (k <= m){
            		MB = matriks[k][j];
	            	for (l = 0; l < n; l++){
	            		matriks[k][l] -= MB * matriks[i][l];
	            	}
	            	k += 1;
            	}
                i += 1;
                j += 1;
            }
        }

        for (i = 0; i < m; i++){
            for (j = 0; j < n; j++){
                if (matriks[i][j] == -0){
                    matriks[i][j] = 0;
                }
            }
        }

		/* MULAI GAUSS JORDAN */
		i = m;
        while (i > 0){
            while (makeZeroBrs(i)){
                i -= 1;
            }           

           	k = i - 1;
           	while (k >= 0){
	         	MB = matriks[k][Pivot(i)];
	           	for (l = 0; l < n; l++){
	           		matriks[k][l] -= MB * matriks[i][l];
		      	}
	           	k -= 1;
        	}
            i -= 1;
        }

        System.out.println("");
        System.out.println("MATRIKS GAUSS JORDAN");
        printMatriks();
    }

    public void solusigaussjordan(){
        int i, j, x, k;
        float temp;
        gaussjordan();


        System.out.println("");
        System.out.println("Solusi : ");
        for (i = 0; i < m; i++) {
            j = 0;
            while ((j < n - 1) && (matriks[i][j] == 0)){
                j++;
            }
            if (j < n - 1){
                for (k = i-1; k >= 0; k--){
                    temp = matriks[k][j];
                    for (x = j; x < n; x++){
                        matriks[k][x] -= (temp*matriks[i][x]);
                    }
                }
            }
        }
        for (i =0; i < m; i++) {
            j = 0;
            while (j < n-1 && matriks[i][j] == 0){
                j++;
            }
            if (j < n-1){
                System.out.printf("%c = %f", (j+65), matriks[i][n-1]);
                for (k = j+1; k < n-1; k++){
                    if (matriks[i][k] > 0){
                        System.out.printf(" - %f%c", matriks[i][k], (k+65));
                    } else if (matriks[i][k] < 0){
                        System.out.printf(" + %f%c", matriks[i][k], (k+65));
                    }
                }
                System.out.println("");
            }
        }
    }

    public void gaussjordanfile(){
    	inputfile();
    	int i, j, x, k;
    	float temp;
    	int m = matriks[0].length;
        int n = matriks.length;
    	gaussjordan();
    

    	System.out.println("");
    	System.out.println("Solusi : ");
        for (i = 0; i < m; i++) {
            j = 0;
            while ((j < n - 1) && (matriks[i][j] == 0)){
                    j++;
            }
            if (j < n - 1){
                for (k = i-1; k >= 0; k--){
                    temp = matriks[k][j];
                    for (x = j; x < n; x++){
                            matriks[k][x] -= (temp*matriks[i][x]);
                    }
                }
            }
        }
        for (i =0; i < m; i++) {
        	j = 0;
            while (j < n-1 && matriks[i][j] == 0){
                j++;
            }
            if (j < n-1){
                System.out.printf("%c = %f", (j+65), matriks[i][n-1]);
                for (k = j+1; k < n-1; k++){
                    if (matriks[i][k] > 0){
                        System.out.printf(" - %f%c", matriks[i][k], (k+65));
                    } else if (matriks[i][k] < 0){
                        System.out.printf(" + %f%c", matriks[i][k], (k+65));
                    }
                }
                System.out.println("");
            }
        }

    }
}  