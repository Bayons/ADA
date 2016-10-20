import java.util.ArrayList;
import java.util.Scanner;

public class Subelementos {

	private static boolean[] soluciones;

	public static void main(String[] args) {
		int numero, suma, actual;
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> princ = new ArrayList<Integer>();

		System.out.println("Introduce los numeros de los elementos del subconjunto (Introduce 0 para continuar)");
		while ((numero = in.nextInt()) > 0)
			princ.add(numero);

		System.out.println("Introduce la suma");
		int sumaBusc = in.nextInt();

		soluciones = new boolean[princ.size()];

		buscar(princ, sumaBusc);
		
		System.out.println("\nFin");
	}

	public static void buscar (ArrayList<Integer> princ, int sumaBusc){
		for (int i = 0; i < princ.size(); i++) {
			if (princ.get(i) == sumaBusc)
				System.out.println("Encontrado: " + princ.get(i));
			else if (princ.get(i) > sumaBusc)
				continue;
			else {
				soluciones[i] = true;
				sumaPosic(i, i, princ.get(i), princ, sumaBusc);
				soluciones[i] = false;
			}
		}
	}
	
	public static void sumaPosic(int pos1, int pos2, int suma, ArrayList<Integer> princ, int sumaBusc) {
		int copiaSuma;
		for (int i = pos2 + 1; i < princ.size(); i++) {
			copiaSuma = suma;
			if ((copiaSuma += princ.get(i)) > sumaBusc)
				continue;
			else if (copiaSuma == sumaBusc) {
				soluciones[i]=true;
				System.out.print("Encontrado: ");
				imprimir(princ);
				soluciones[i]=false;
			} else {
				soluciones[i]=true;
				sumaPosic(pos1, i, copiaSuma, princ, sumaBusc);
				soluciones[i]=false;
			}
		}
	}
	
	public static void imprimir(ArrayList<Integer> princ){
		for (int i = 0; i < princ.size(); i++){
			if (soluciones[i])
				System.out.print(princ.get(i)+" ");
		}
		System.out.println();
	}
}
