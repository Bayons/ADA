
public class Metodos {

	/*
	* Metodo para realiza una busqueda binaria
	* @return contadorBin int con el numero de operaciones elementales
	*/
	public static int busquedaBinaria(int vector[], int dato) {
		int n = vector.length, contadorBin=0;
		int centro, inf = 0, sup = n - 1;
		while (inf <= sup) {
			centro = (sup + inf) / 2;
			contadorBin++;
			if (vector[centro] == dato)
				return contadorBin;
			else if (dato < vector[centro]) {
				sup = centro - 1;
			} else {
				inf = centro + 1;
			}contadorBin++;
		}return contadorBin;
	}

	/*
	 * Metodo que realiza una busqueda secuencial
	 * @return contadorSec int con el numero de operaciones elementales
	 */
	public static int busquedaSecuencial(int[] vector, int dato) {
		int contadorSec=0;
		for (int i = 0; i < vector.length; i++) {
			contadorSec++;
			if (vector[i] == dato) {
				return contadorSec;
			}
		}return contadorSec;
	}
	
	/*
	 * Crea un vector aleatorio de variable aleatoria maxima el tamano
	 */
	public static void creaVector(int TAMANO,int[] vector){
		for (int i = 0; i < TAMANO; i++) {
			vector[i] = (int) (Math.random() * TAMANO);
		}
	}
	
	/*
	 * Ordena el vector
	 */
	public static void ordenaVector(int TAMANO, int[] vector){
		int h,tmp;
		for (int j = 1; j < TAMANO; j++) {
			h = j;
			while (h > 0 && vector[h] < vector[h - 1]) {
				tmp = vector[h];
				vector[h] = vector[h - 1];
				vector[h - 1] = tmp;
				h--;
			}
		}
	}
}
