import java.io.*;

public class BusquedaOrdenada extends Metodos {

	public static void iterarOrdenado(int TAMANO, BufferedWriter bsec, BufferedWriter bsecM, BufferedWriter bbin,
			BufferedWriter bbinM) throws IOException {
		final int REPETICIONES = 30;
		int contadorBin = 0, contadorSec = 0;
		int[] vector = new int[TAMANO];
		int valorBuscado;
		long timeIni, timeEnd, timeSec = 0, timeBin = 0;

		creaVector(TAMANO, vector);
		ordenaVector(TAMANO, vector);

		for (int t = 0; t < REPETICIONES; t++) {
			valorBuscado = (int) (Math.random() * TAMANO); // random

			timeIni = System.nanoTime();
			contadorBin += busquedaBinaria(vector, valorBuscado);
			timeEnd = System.nanoTime();
			timeBin += (timeEnd - timeIni);
			System.out.println("\nREPETICION " + t + "\nBinario:");
			System.out.println("\tComparaciones: " + busquedaBinaria(vector, valorBuscado));
			System.out.println("\tTiempo: " + (timeEnd - timeIni));

			bsec.write("\nREPETICION " + t + "\nBinario:");
			bsec.write("\tComparaciones: " + busquedaBinaria(vector, valorBuscado));
			bsec.write("\tTiempo: " + (timeEnd - timeIni));

			timeIni = System.nanoTime();
			contadorSec += busquedaSecuencial(vector, valorBuscado);
			timeEnd = System.nanoTime();
			timeSec += (timeEnd - timeIni);
			System.out.println("Secuencial:");
			System.out.println("\tComparaciones: " + busquedaSecuencial(vector, valorBuscado));
			System.out.println("\tTiempo: " + (timeEnd - timeIni));
		}
		System.out.println("\nMEDIA:");
		System.out.println("Binario:\n\tTiempo: " + (timeBin / REPETICIONES) + " nanosegundos.");
		System.out.println("\tComparaciones: " + contadorBin / REPETICIONES);
		System.out.println("Secuencial:\n\tTiempo " + (timeSec / REPETICIONES) + " nanosegundos.");
		System.out.println("\tComparaciones: " + contadorSec / REPETICIONES);
	}

	public static void main(String args[]) {
		String sec = "sec.txt", secM = "secMedia.txt", bin = "bin.txt", binM = "binMedia.txt";
		FileWriter fsec, fsecM, fbin, fbinM;

		try {
			fsec = new FileWriter(sec);
			fsecM = new FileWriter(secM);
			fbin = new FileWriter(bin);
			fbinM = new FileWriter(binM);

			BufferedWriter bsec = new BufferedWriter(fsec);
			BufferedWriter bsecM = new BufferedWriter(fsecM);
			BufferedWriter bbin = new BufferedWriter(fbin);
			BufferedWriter bbinM = new BufferedWriter(fbinM);

			for (int TAMANO = 10; TAMANO < 100001; TAMANO *= 10) {
				System.out.println("\n---------------------\n    TAMA�O: " + TAMANO + "\n--------------------- \n");
				iterarOrdenado(TAMANO, bsec, bsecM, bbin, bbinM);
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}