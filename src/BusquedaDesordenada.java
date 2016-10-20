import java.io.FileWriter;
import java.io.IOException;

public class BusquedaDesordenada extends Metodos{
	
	public static void iterarDesordenado(int TAMANO,FileWriter total, FileWriter medias) throws IOException{
		final int REPETICIONES = 30;
		int contadorBin=0,contadorSec = 0;
		int[] vector = new int[TAMANO];
		int valorBuscado;
		long timeIni, timeEnd, timeSec = 0, timeBin = 0,timeS =0, timeB = 0;

		medias.write("\r\n\r\nTAMAÑO: " + TAMANO + "\r\n\r\n\tBINARIO\t\t\tSECUENCIAL\r\n");
		total.write("\r\nTAMAÑO: " + TAMANO + "\t\tBINARIO\t\t\t\t\t\tSECUENCIAL\r\n");
		for (int t = 0; t < REPETICIONES; t++) {
			 
			creaVector(TAMANO,vector);
			
			valorBuscado = (int) (Math.random() * TAMANO); // random
			timeIni = System.nanoTime();
			contadorSec += busquedaSecuencial(vector, valorBuscado);
			timeEnd = System.nanoTime();
			timeS = (timeEnd - timeIni);
			timeSec += (timeEnd - timeIni);
			
			timeIni = System.nanoTime();
			contadorBin += ordenaVector(TAMANO, vector);
			contadorBin += busquedaBinaria(vector, valorBuscado);
			timeEnd = System.nanoTime();
			timeB = (timeEnd - timeIni);
			timeBin += (timeEnd - timeIni);
			
			total.write("\r\n" + (t + 1));
			total.write("\tComparaciones: " + busquedaBinaria(vector, valorBuscado) + "\tTiempo: " + (timeB));
			total.write("\t\tComparaciones: " + busquedaSecuencial(vector, valorBuscado) + "\tTiempo: " + (timeS));
		}
		total.write("\r\n\r\n");
		medias.write("Tiempo: " + (timeBin / REPETICIONES) + "\t\tTiempo: " + (timeSec / REPETICIONES)
				+ "\r\nComparaciones: " + (contadorBin / REPETICIONES) + "\tComparaciones: "
				+ (contadorSec / REPETICIONES) + "\r\n");
	}
	
	public static void main(String args[]){
		FileWriter total = null, medias = null;
		try {
			total = new FileWriter("DesordenadoTotal.txt");
			medias = new FileWriter("DesordenadoMedia.txt");

			total.write("DESORDENADO: datos experimentales\r\n");
			medias.write("DESORDENADO: medias\r\n");
			for (int TAMANO = 10; TAMANO < 100001; TAMANO *= 10) {
				iterarDesordenado(TAMANO, total, medias);
			}
			total.close();
			medias.close();

		} catch (IOException e) {
			System.out.println(e);
		}

	}
}
