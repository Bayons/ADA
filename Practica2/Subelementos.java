import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Scanner;

public class Subelementos {

	private static boolean[] soluciones;

	/**
	 * Método que llama al metodo recursivo sumaPosic pasandole con la posicion
	 * fija del subconjunto
	 * 
	 * @param princ
	 *            ArrayList<Integer> con el conjunto completo de numeros
	 *            introducidos por el usuario
	 * @param sumaBusc
	 *            int con el numero buscado por el usuario
	 * @param fich
	 *            FileWriter para sacar las soluciones a un fichero
	 * @throws IOException
	 *             saltando cualquier excepcion producida por la creacion y/o
	 *             uso del fichero
	 */
	public static void buscar(ArrayList<Integer> princ, int sumaBusc, FileWriter fich) throws IOException {
		for (int i = 0; i < princ.size(); i++) {
			if (princ.get(i) == sumaBusc) {
				soluciones[i] = true;
				imprimir(princ, fich);
				soluciones[i] = false;
			} else if (princ.get(i) > sumaBusc)
				continue;
			else {
				soluciones[i] = true;
				sumaPosic(i, i, princ.get(i), princ, sumaBusc, fich);
				soluciones[i] = false;
			}
		}
	}

	/**
	 * Metodo que busca de manera recursiva un subconjunto cuya suma es la
	 * introducida por el usuario
	 * 
	 * @param posFija
	 *            int que define el primer parametro del subconjunto del cual
	 *            busca una solucion
	 * @param posVar
	 *            int que varía en cada iteracion indicando el final del
	 *            subconjunto
	 * @param sumaParc
	 *            int que contiene la suma actual del subconjunto
	 * @param princ
	 *            ArrayList<Integer> con el conjunto completo de numeros
	 *            introducidos por el usuario
	 * @param sumaBusc
	 *            int con el numero buscado por el usuario
	 * @param fich
	 *            FileWriter para sacar las soluciones a un fichero
	 * @throws IOException
	 *             saltando cualquier excepcion producida por la creacion y/o
	 *             uso del fichero
	 */
	public static void sumaPosic(int posFija, int posVar, int sumaParc, ArrayList<Integer> princ, int sumaBusc,
			FileWriter fich) throws IOException {
		int copiaSuma;
		for (int i = posVar + 1; i < princ.size(); i++) {
			copiaSuma = sumaParc;
			if ((copiaSuma += princ.get(i)) > sumaBusc)
				continue;
			else if (copiaSuma == sumaBusc) {
				soluciones[i] = true;
				imprimir(princ, fich);
				soluciones[i] = false;
			} else {
				soluciones[i] = true;
				sumaPosic(posFija, i, copiaSuma, princ, sumaBusc, fich);
				soluciones[i] = false;
			}
		}
	}

	/**
	 * Metodo que escribe en un fichero los distintos subconjuntos de soluciones
	 * comparando el vector introducido por el usuario con el vector de
	 * soluciones
	 * 
	 * @param princ
	 *            ArrayList<Integer> con el conjunto completo de numeros
	 *            introducidos por el usuario
	 * @param fich
	 *            FileWriter para sacar las soluciones a un fichero
	 * @throws IOException
	 *             saltando cualquier excepcion producida por la creacion y/o
	 *             uso del fichero
	 */
	public static void imprimir(ArrayList<Integer> princ, FileWriter fich) throws IOException {
		boolean primero = true;
		for (int i = 0; i < princ.size(); i++) {
			if (soluciones[i]) {
				if (primero) {
					fich.write(princ.get(i) + "");
					primero = false;
				} else
					fich.write(", " + princ.get(i));
			}
		}
		fich.write("\r\n");
	}

	/*
	 * Metodo principal en el que se pide al usuario un conjunto de numeros para
	 * crear el vector princ y la suma buscada, inicializa el vector soluciones
	 * para indicar que numeros del conjunto estamos tomando en el subconjunto y
	 * crea el fichero de salida
	 */
	public static void main(String[] args) {
		int numero;
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> princ = new ArrayList<Integer>();
		FileWriter fich;

		System.out.println("Introduce los numeros de los elementos del subconjunto (Introduce 0 para continuar)");
		while ((numero = in.nextInt()) > 0)
			princ.add(numero);

		System.out.println("Introduce la suma");
		int sumaBusc = in.nextInt();

		soluciones = new boolean[princ.size()];

		try {
			fich = new FileWriter("salida_p2_migbayo_danpare.txt");
			buscar(princ, sumaBusc, fich);
			fich.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
