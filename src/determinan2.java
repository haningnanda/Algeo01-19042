import java.util.*;
import java.io.*;
import java.lang.*;

public class determinan2 {

	int n = 0;
	float[][] matriks = new float[300][300];


	determinan2(){
		int i, j;

		for (i = 0; i <= n; i++){
			for (j = 0; j <= n; j++){
				this.matriks[i][j] = 0;
			}
		}
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

    public void readMatriksPersegiUser(){
		/* BACA INPUT MATRIKS DARI USER */

		Scanner in = new Scanner(System.in);

        // Scan n
        System.out.print("Masukkan jumlah kolom dan baris : ");
        n = in.nextInt();
	}
	
	public void writeMatriksPersegi(){
		/* MASUKKAN ELEMEN MATRIKS FROM USER*/
		int i, j;
		Scanner in = new Scanner(System.in);

        System.out.println("Masukkan elemen matriks : ");
        for (i = 0; i < n; i++){
            for (j = 0; j < n; j++){
                System.out.print("Masukkan indeks baris ke-" + i + " kolom ke-" + j + " : ");
                matriks[i][j] = in.nextInt();
            }
        }
	}

	public void printMatriksPersegi(){
		/* PRINT MATRIKS*/
		int i, j;
        System.out.println("");
        System.out.println("Matriks " + n + " x " + n + " yang dibuat : ");
        for (i = 0; i < n; i++){
            for (j = 0; j < n; j++){
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println("");
        }
	}

	public void determinan(){
		int i, j, k;
		boolean found = false;
		float swap = 1;
		float temp, MB; // matriks baru

		System.out.println("");
        System.out.println("Matriks setelah di reduksi : ");
		for (i = 0; i < n; i++){
			if (matriks[i][i] == 0){
				for (j = i + 1; j < n; j++){
					if ((matriks[j][i] != 0) && (found == false)){
						for (k = 0; k < n; k++){
							temp = matriks[i][k];
							matriks[i][k] = matriks[j][k];
							matriks[j][k] = temp;
						}
						swap *= -1;
						found = true;
					}
				}
			}
			for (j = 0; j < n; j++){
				if (i != j){
					if (matriks[j][i] != 0){
						MB = matriks[j][i] / matriks[i][i];
						
						for (k = 0; k < n; k++){
							matriks[j][k] -= (matriks[i][k] * MB);						
						}
					}
				}
				System.out.print(matriks[i][j] + " ");
			}
			System.out.println(" ");
		}
        
        System.out.println("");
        for (i = 0; i < n; i++){
			swap *= matriks[i][i];
		}
		System.out.print("Hasil Determinan : " + (int) swap);
	}

    public void determinanfile(){
        inputfile();
        int m= matriks[0].length;
        int n = matriks.length;

        int i, j, k;
        boolean found = false;
        float swap = 1;
        float temp, MB; // matriks baru

        System.out.println("");
        System.out.println("Matriks setelah di reduksi : ");
        for (i = 0; i < n; i++){
            if (matriks[i][i] == 0){
                for (j = i + 1; j < n; j++){
                    if ((matriks[j][i] != 0) && (found == false)){
                        for (k = 0; k < n; k++){
                            temp = matriks[i][k];
                            matriks[i][k] = matriks[j][k];
                            matriks[j][k] = temp;
                        }
                        swap *= -1;
                        found = true;
                    }
                }
            }
            for (j = 0; j < n; j++){
                if (i != j){
                    if (matriks[j][i] != 0){
                        MB = matriks[j][i] / matriks[i][i];
                        
                        for (k = 0; k < n; k++){
                            matriks[j][k] -= (matriks[i][k] * MB);                      
                        }
                    }
                }
                System.out.print(matriks[i][j] + " ");
            }
            System.out.println(" ");
        }
        
        System.out.println("");
        for (i = 0; i < n; i++){
            swap *= matriks[i][i];
        }
        System.out.print("Hasil Determinan : " + (int) swap);
    }

}
