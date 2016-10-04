import java.io.*;

public class BusquedaOrdenada extends Metodos {

	public static void iterarOrdenado(int TAMANO, FileWriter sec, FileWriter secM, FileWriter bin,
			FileWriter binM) throws IOException {
		final int REPETICIONES = 30;
		int contadorBin = 0, contadorSec = 0;
		int[] vector = new int[TAMANO];
		int valorBuscado;
		long timeIni, timeEnd, timeSec = 0, timeBin = 0;

		creaVector(TAMANO, vector);
		ordenaVector(TAMANO, vector);
		
		
		sec.write("\r\n\r\n\r\nTAMAÑO: "+TAMANO+"\r\n");
		bin.write("\r\n\r\n\r\nTAMAÑO: "+TAMANO+"\r\n");
		secM.write("\r\nTAMAÑO: "+TAMANO+"\r\n");
		binM.write("\r\nTAMAÑO: "+TAMANO+"\r\n");
		for (int t = 0; t < REPETICIONES; t++) {
			valorBuscado = (int) (Math.random() * TAMANO); // random

			timeIni = System.nanoTime();
			contadorBin += busquedaBinaria(vector, valorBuscado);
			timeEnd = System.nanoTime();
			timeBin += (timeEnd - timeIni);
			System.out.println("\nREPETICION " + t + "\nBinario:");
			System.out.println("\tComparaciones: " + busquedaBinaria(vector, valorBuscado));
			System.out.println("\tTiempo: " + (timeEnd - timeIni));
			bin.write("\r\nREPETICION " + (t+1));
			bin.write("\tComparaciones: " + busquedaBinaria(vector, valorBuscado));
			bin.write("\tTiempo: " + (timeEnd - timeIni));
			
			

			timeIni = System.nanoTime();
			contadorSec += busquedaSecuencial(vector, valorBuscado);
			timeEnd = System.nanoTime();
			timeSec += (timeEnd - timeIni);
			sec.write("\r\nREPETICION " + (t+1));
			sec.write("\tComparaciones: " + busquedaSecuencial(vector, valorBuscado));
			sec.write("\tTiempo: " + (timeEnd - timeIni));
			
			System.out.println("Secuencial:");
			System.out.println("\tComparaciones: " + busquedaSecuencial(vector, valorBuscado));
			System.out.println("\tTiempo: " + (timeEnd - timeIni));
		}
		secM.write("Tiempo: " + (timeSec / REPETICIONES)+"\r\nComparaciones: " + (contadorSec / REPETICIONES)+"\r\n");
		binM.write("Tiempo: " + (timeBin / REPETICIONES)+"\r\nComparaciones: " + (contadorBin / REPETICIONES)+"\r\n");
		
		System.out.println("\nMEDIA:");
		System.out.println("Binario:\n\tTiempo: " + (timeBin / REPETICIONES) + " nanosegundos.");
		System.out.println("\tComparaciones: " + contadorBin / REPETICIONES);
		System.out.println("Secuencial:\n\tTiempo " + (timeSec / REPETICIONES) + " nanosegundos.");
		System.out.println("\tComparaciones: " + contadorSec / REPETICIONES);
	}

	public static void main(String args[]) {
		FileWriter sec = null,secM = null,bin = null,binM = null;
		try {
			sec = new FileWriter("SecuencialTotal.txt");
			secM = new FileWriter("SecuencialMedia.txt");
			bin = new FileWriter("BinarioTotal.txt");
			binM = new FileWriter("BinarioMedia.txt");
			
			sec.write("SECUENCIAL: datos experimentales\r\n");
			bin.write("BINARIO: datos experimentales\r\n");
			secM.write("SECUENCIAL: medias\r\n");
			binM.write("BINARIO: medias\r\n");
			for (int TAMANO = 10; TAMANO < 100001; TAMANO *= 10) {
				System.out.println("\n---------------------\n    TAMAÑO: " + TAMANO + "\n--------------------- \n");
				iterarOrdenado(TAMANO, sec, secM, bin, binM);
			}
			sec.close();
			secM.close();
			bin.close();
			binM.close();
			
		} catch (IOException e) {
			System.out.println(e);
		}
			

	}
}