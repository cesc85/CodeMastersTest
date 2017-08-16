import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

public class Pankakes {
	
	private static final String INPUT_FILE_S = "C:/Users/fr.giordano/Downloads/codemasters/A-small-practice.in";
	private static final String OUTPUT_FILE_S = "C:/Users/fr.giordano/Downloads/codemasters/A-sol-small.txt";
	private static final String INPUT_FILE_L = "C:/Users/fr.giordano/Downloads/codemasters/A-large-practice.in";
	private static final String OUTPUT_FILE_L = "C:/Users/fr.giordano/Downloads/codemasters/A-sol-large.txt";

    public static void main(String[] args) throws IOException {
		Scanner sc = null;
        File inputFile = new File(INPUT_FILE_S);
        File outputFile = new File(OUTPUT_FILE_S);
        FileOutputStream fos = new FileOutputStream(outputFile);
        PrintWriter pw = new PrintWriter(fos);
        sc = new Scanner(inputFile);
    	try{
            int t = sc.nextInt();
			for (int i = 1; i <= t; ++i) {
				String s = sc.next();
				int k = Integer.valueOf(sc.next());
				//vero core del test
				String ret = flip(s, k);
				pw.write("Case #" + i + ": " + ret+"\n");
			}
	        pw.close();
    	} catch (Exception e){
    		System.out.println(e.getMessage());
    		pw.close();
    		sc.close();
    		fos.close();
    	}
    }
    
    //costo O(n) dove n è la lunghezza della stringa
    public static String flip(String s, int k){
    	int n = s.length();
    	char[] charArr = s.toCharArray();
    	int ret = 0;
    	for(int i = 0; i<=n-k; i++){
    		if('-' == charArr[i]){
    			ret++;
    			//giro k elementi
    			for(int j = 0; j < k; j++){
    				charArr[i + j] = charArr[i + j] == '-' ? '+' : '-';
    			}
    		}
    	}
    	//controllo se sono rimasti dei - dove non posso più arrivare con questo metodo
    	for(int i = n-k; i < n; i++){
    		if('-' == charArr[i]){
    			return "IMPOSSIBLE";
    		}
    	}
    	return String.valueOf(ret);
    }
}

