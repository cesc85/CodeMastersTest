import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TidyNumbers {
	
	private static final String INPUT_FILE_S = "C:/Users/fr.giordano/Downloads/codemasters/B-small-practice.in";
	private static final String OUTPUT_FILE_S = "C:/Users/fr.giordano/Downloads/codemasters/B-sol-small.txt";
	private static final String INPUT_FILE_L = "C:/Users/fr.giordano/Downloads/codemasters/B-large-practice.in";
	private static final String OUTPUT_FILE_L = "C:/Users/fr.giordano/Downloads/codemasters/B-sol-large..txt";

    public static void main(String[] args) throws IOException {
		Scanner sc = null;
        File inputFile = new File(INPUT_FILE_S);
        File outputFile = new File(OUTPUT_FILE_S);
        FileOutputStream fos = new FileOutputStream(outputFile);
        PrintWriter pw = new PrintWriter(fos);
        sc = new Scanner(inputFile);
		String n = "";
    	try{
            int t = sc.nextInt();
			for (int i = 1; i <= t; ++i) {
				n = sc.next();
				//vero core del test
				String ret = clean(n);
				pw.write("Case #" + i + ": " + ret+"\n");
			}
	        pw.close();
    	} catch (Exception e){
    		System.out.println("input: " + n + " " +e.getMessage());
    		pw.close();
    		sc.close();
    		fos.close();
    	}
    }
    
    //costo O(l) dove l è il numero di cifre di input
    public static String clean(String n){
    	int l = n.length();
    	//caso base ad una cifra
    	if(l == 1){
    		return n;
    	}
    	int lastGrowingIndex = 0;
    	char[] charArr = n.toCharArray();
    	for(int i = 0; i<l-1; i++){
    		//cerco dove ho la prima decrescenza
    		if(charArr[i] > charArr[i+1]){
    			charArr = fixNumber(charArr, lastGrowingIndex);
    			break;
    		}
    		//mi salvo l'ultima volta che il numero è cresciuto, parto da questo indice per mettere tutti 9 in seguito
    		else if (charArr[i] < charArr[i+1]){
    			lastGrowingIndex = i + 1;
    		}
    	}
    	return String.valueOf(charArr);
    }
    
    public static char[] fixNumber(char[] charArr, int index){
    	int len = charArr.length;
		if(charArr[index] != '1'){
			//diminuisco di 1 il valore della cifra interessata e metto tutte le cifre a destra pari a 9
			int actualVal = Integer.valueOf(charArr[index]);
			actualVal -= 1;
			charArr[index] = (char) actualVal;
			for(int j = index + 1; j < len; j++){
				charArr[j] = '9';
			}
		} else{
			//caso con solo 1 e 0 come radice, scrivo tanti '9' quanto il num originario di cifre meno 1
			charArr = new char[len-1];
			for(int j = 0; j < len-1 ; j++){
				charArr[j] = '9';
			}
		}
    	return charArr;
    }
}

