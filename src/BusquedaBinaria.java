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
		final int TAMANO = 1000; // incrementando
		final int REPETICIONES = 30;
		int[] vector = new int[TAMANO];
		int h, tmp, valorBuscado;
		long timeIni, timeEnd, timeSec = 0, timeBin = 0;

		for (int i = 0; i < TAMANO; i++) {
			vector[i] = (int) (Math.random() * TAMANO);
		}

		for (int j = 1; j < TAMANO; j++) {
			h = j;
			while (h > 0 && vector[h] < vector[h - 1]) {
				tmp = vector[h];
				vector[h] = vector[h - 1];
				vector[h - 1] = tmp;
				h--;
			}
		}

		for (int t = 0; t < REPETICIONES; t++) {
			valorBuscado = (int) (Math.random() * TAMANO); // random

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
}