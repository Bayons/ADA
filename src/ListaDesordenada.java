
public class ListaDesordenada {

	public static void main(String[] args) {
		int tam = 1000000; // incrementando
		int[] vector = new int[tam];
		int valorBuscado;
		long timeIni, timeEnd, timeSec = 0, timeBin = 0;
		final int REPETICIONES = 30;

		for (int i = 0; i < tam; i++) {
			vector[i] = (int) (Math.random() * tam);
		}

		for (int t = 0; t < REPETICIONES; t++) {
			valorBuscado = (int) (Math.random() * tam); // random
			timeIni = System.nanoTime();
			busquedaSecuencial(vector, valorBuscado);
			timeEnd = System.nanoTime();
			timeSec += (timeEnd - timeIni);
		}
		System.out.println("Secuencial: " + (timeSec / REPETICIONES) + " nanosegundos.");
	}

	
	public static void ordena (int vector[]){
		int h, tmp;
		for (int j = 1; j < vector.length; j++) {
			h = j;
			while (h > 0 && vector[h] < vector[h - 1]) {
				tmp = vector[h];
				vector[h] = vector[h - 1];
				vector[h - 1] = tmp;
				h--;
			}
		}
	}
	
	public static void busquedaBinaria(int vector[], int dato) {
		
		
		
		int n = vector.length;
		int centro, inf = 0, sup = n - 1;
		while (inf <= sup) {
			centro = (sup + inf) / 2;
			if (vector[centro] == dato)
				break;
			else if (dato < vector[centro]) {
				sup = centro - 1;
			} else {
				inf = centro + 1;
			}
		}
	}
	
	public static void busquedaSecuencial(int[] vector, int dato) {
		for (int i = 0; i < vector.length; i++) {
			if (vector[i] == dato) {
				break;
			}
		}
	}
}
