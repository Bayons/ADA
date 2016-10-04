

public class BusquedaDesordenada extends Metodos{
	
	public static void iterarDesordenado(int TAMANO) {
		final int REPETICIONES = 30;
		int contadorBin=0,contadorSec = 0;
		int[] vector = new int[TAMANO];
		int valorBuscado;
		long timeIni, timeEnd, timeSec = 0, timeBin = 0;

		
		creaVector(TAMANO,vector);

		for (int t = 0; t < REPETICIONES; t++) {
			valorBuscado = (int) (Math.random() * TAMANO); // random

			timeIni = System.nanoTime();
			ordenaVector(TAMANO, vector);
			contadorBin += busquedaBinaria(vector, valorBuscado);
			timeEnd = System.nanoTime();
			timeBin += (timeEnd - timeIni);
			System.out.println("\nREPETICION "+t+"\nBinario:");
			System.out.println("\tComparaciones: "+busquedaBinaria(vector, valorBuscado));
			System.out.println("\tTiempo: "+(timeEnd - timeIni));
			

			timeIni = System.nanoTime();
			contadorSec += busquedaSecuencial(vector, valorBuscado);
			timeEnd = System.nanoTime();
			timeSec += (timeEnd - timeIni);
			System.out.println("Secuencial:");
			System.out.println("\tComparaciones: "+busquedaSecuencial(vector, valorBuscado));
			System.out.println("\tTiempo: "+(timeEnd - timeIni));
		}
		System.out.println("\nMEDIA:");
		System.out.println("Binario:\n\tTiempo: " + (timeBin / REPETICIONES) + " nanosegundos.");
		System.out.println("\tComparaciones: "+contadorBin/REPETICIONES);
		System.out.println("Secuencial:\n\tTiempo " + (timeSec / REPETICIONES) + " nanosegundos.");
		System.out.println("\tComparaciones: "+contadorSec/REPETICIONES);
	}
	
	public static void main(String args[]){
		for(int TAMANO=10;TAMANO<100001;TAMANO*=10){
			System.out.println("\n---------------------\n    TAMAÑO: "+TAMANO+"\n--------------------- \n");
			iterarDesordenado(TAMANO);
		}
	}
}
