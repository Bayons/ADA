
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

	public static void busquedaSecuencial(int[] vector, int dato) {
		for (int i = 0; i < vector.length; i++) {
			if (vector[i] == dato) {
				break;
			}
		}
	}
}
