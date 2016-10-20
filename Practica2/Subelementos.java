import java.util.ArrayList;
import java.util.Scanner;

public class Subelementos {

	public static void main(String[] args) {
		int numero, suma, actual;
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> princ = new ArrayList<Integer>();

		System.out.println("Introduce los numeros de los elementos del subconjunto");
		while ((numero = in.nextInt()) != 0) {
			princ.add(numero);
		}
		System.out.println("Introduce la suma");
		int sumaBusc = in.nextInt();

		for (int i = 0; i < princ.size(); i++) {
			princ.get(i)+princ.get(i+1) = suma;
		}
	}
	
	public static void suma (int pos1, int pos2, int suma, ArrayList<Integer> princ, int sumaBusc){
		for (int i = pos2; i<princ.size(); i++){
			if (pos1==i) continue;
			if(suma+princ.get(i)<sumaBusc) suma(pos1, i, (suma+princ.get(i)), princ, sumaBusc);
			else if(suma+princ.get(i)==sumaBusc) System.out.println("Encontrado");
		}
	}

}
