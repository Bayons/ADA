class BusquedaBinaria {

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

	public static void main(String[] args) {
		int tam = 1000; // incrementando
		int[] vector = new int[tam];
		int h, tmp, valorBuscado;
		long timeIni, timeEnd, timeSec = 0, timeBin = 0;
		final int REPETICIONES = 30;

		for (int i = 0; i < tam; i++) {
			vector[i] = (int) (Math.random() * tam);
		}

		for (int j = 1; j < tam; j++) {
			h = j;
			while (h > 0 && vector[h] < vector[h - 1]) {
				tmp = vector[h];
				vector[h] = vector[h - 1];
				vector[h - 1] = tmp;
				h--;
			}
		}

		for (int t = 0; t < REPETICIONES; t++) {
			valorBuscado = (int) (Math.random() * tam); // random

			timeIni = System.nanoTime();
			busquedaBinaria(vector, valorBuscado);
			timeEnd = System.nanoTime();
			timeBin += (timeEnd - timeIni);

			timeIni = System.nanoTime();
			busquedaSecuencial(vector, valorBuscado);
			timeEnd = System.nanoTime();
			timeSec += (timeEnd - timeIni);
		}
		System.out.println("Binario: " + (timeBin / REPETICIONES) + " nanosegundos.");
		System.out.println("Secuencial: " + (timeSec / REPETICIONES) + " nanosegundos.");
	}
	/*
	 * --- TIEMPOS DE BUSQUEDA (ns) ---
	 * Tamaño				Binario				Secuencial
	 * 1000000				12783				1413746
	 * 1000000				7308				1193761
	 * 100					902					2736
	 * 100					1290				6329
	 * 100					1290				3499
	 * 1000					1368				4416
	 * 1000					2285				8895
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}