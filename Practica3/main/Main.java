package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import usuario.User;

/**
 * @author Daniel Paredes Santamaria
 * @author Miguel Bayon Sanz
 * @version 1.0
 */
public class Main {

	/**
	 * Metodo principal por el que se lee el fichero especificado por el
	 * usuario, se crean los usuarios indicados en el fichero de entrada y se
	 * imprime si el usuario lo indica la matriz de adyacencia (que representa a
	 * quien ve cada usuario) y los usuarios a los que sigue cada uno
	 */
	public static void main(String[] args) {
		String cadena, fichero;
		String[] linea;
		ArrayList<User> usuarios = new ArrayList<User>();
		User user1 = null, user2 = null;
		boolean[][] mAdy;
		boolean consola = false;
		String entrada;

		System.out.println("Introduce el nombre del fichero que deseas cargar");
		Scanner sc = new Scanner(System.in);
		fichero = sc.nextLine();

		System.out
				.println("¿Quieres la matriz de adyacencia y la lista de quién sigue a quién? Y/N (Otra entrada = N)");
		entrada = sc.nextLine().toLowerCase();
		if (entrada.equals("y")) {
			consola = true;
		} else if (entrada.equals("n")) {
			consola = false;
		}
		sc.close();
		
		// Creacion de usuarios
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			while ((cadena = br.readLine()) != null) {
				if (cadena.contains(" follows ")) {
					linea = cadena.split(" follows ");

					if ((user1 = getUserPorNombre(usuarios, linea[0])) == null) {
						user1 = new User(linea[0]);
						usuarios.add(user1);
					}

					if ((user2 = getUserPorNombre(usuarios, linea[1])) == null) {
						user2 = new User(linea[1]);
						usuarios.add(user2);
					}
					user1.sigueA(user2);
				}
			}

			mAdy = new boolean[usuarios.size()][usuarios.size()];
			// mAdy[i][j] -> si i sigue a j, entonces true

			for (int i = 0; i < usuarios.size(); i++) {
				for (int j = 0; j < usuarios.size(); j++) {
					if (usuarios.get(i).puedeLeer(usuarios.get(j)))
						mAdy[i][j] = true;
					else
						mAdy[i][j] = false;
				}
			}
			if (consola) {
				imprimeMatriz(mAdy, usuarios);

				for (int i = 0; i < usuarios.size(); i++) {
					System.out.println("El usuario " + usuarios.get(i) + " sigue a --> ");
					for (int j = 0; j < usuarios.get(i).getSiguiendo().size(); j++) {
						System.out.println(usuarios.get(i).getSiguiendo().get(j));
					}
					System.out.println();
				}

				System.out.println("----------------------\n");
			}

			buscaComunidades(usuarios);

			br.close();
			fr.close();
		} catch (FileNotFoundException e1) {
			System.out.println("Archivo no encontrado");
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Busca en la lista de usuarios aquel cuyo nnombre coincida, devolviendolo
	 * 
	 * @param usuarios
	 *            ArrayList de User con los usuarios leidos en el fichero
	 * @param nombre
	 *            String con el nombre del usuario que estamos buscando
	 * @return User con el nombre dado o null si no lo ha encontrado
	 */
	public static User getUserPorNombre(ArrayList<User> usuarios, String nombre) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getNombre().equals(nombre))
				return usuarios.get(i);
		}
		return null;
	}

	/**
	 * Comprueba las comunidades existentes en caso de que las haya utilizando
	 * las listas de usuarios a los que ve cada uno
	 * 
	 * @param usuarios
	 *            ArrayList de User con los usuarios cuyas comunidades se
	 *            quieren buscar
	 */
	public static void buscaComunidades(ArrayList<User> usuarios) {
		FileWriter fich = null;
		int j, k;
		boolean primero;
		try {
			fich = new FileWriter("salida_p3_migbayo_danpare.txt");

			boolean[] hasComunidad = new boolean[usuarios.size()];
			ArrayList<User> comunidad;

			for (int i = 0; i < usuarios.size(); i++) {
				if (!hasComunidad[i] && (comunidad = getComunidades(usuarios.get(i))) != null) {
					primero = true;
					fich.write("COMUNIDAD:\r\n");
					for (k = 0; k < comunidad.size(); k++) {
						if (primero) {
							fich.write(comunidad.get(k) + "");
							primero = false;
						} else
							fich.write(", " + comunidad.get(k));
						// fich.write(comunidad.get(k).getNombre() + " ");
					}
					fich.write("\r\n");
					for (j = 0; j < comunidad.size(); j++)
						hasComunidad[usuarios.indexOf(comunidad.get(j))] = true;
					fich.write("\r\n");
				}
			}
			fich.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Busca y comprueba que el usuario dado se encuentra en una comunidad,
	 * comprobando además que sea posible
	 * 
	 * @param user
	 *            User del que se quiere saber si está en una comunidad
	 * @return ArrayList de User con los usuarios de la comunidad o null en caso
	 *         de que no haya comunidad
	 */
	public static ArrayList<User> getComunidades(User user) {
		ArrayList<User> comunidad = user.aQuienVe();

		for (int i = 0; i < comunidad.size(); i++) {
			for (int j = 0; j < comunidad.size(); j++) {
				if (!comunidad.get(i).puedeLeer(comunidad.get(j))) {
					comunidad.remove(i);
					i--;
					break;
				}
			}
		}
		if (comunidad.size() >= 3)
			return comunidad;
		return null;
	}

	/**
	 * Imprime una matriz que representa la gente a la que puede ver cada
	 * usuario
	 * 
	 * @param mAdy
	 *            boolean[][] que representa a quien puede ver cada usuario
	 * @param usuarios
	 *            ArrayList de User con los usuarios
	 */
	public static void imprimeMatriz(boolean[][] mAdy, ArrayList<User> usuarios) {
		int i;
		for (i = 0; i < usuarios.size(); i++)
			System.out.print(usuarios.get(i).getNombre().charAt(0) + "|");
		System.out.println();
		for (i = 0; i < usuarios.size(); i++)
			System.out.print("--");
		System.out.println();
		for (i = 0; i < usuarios.size(); i++) {
			for (int j = 0; j < usuarios.size(); j++) {
				if (mAdy[i][j]) {
					System.out.print("X|");
				} else {
					System.out.print(" |");
				}
			}
			System.out.println(" " + usuarios.get(i));
		}
		System.out.println();
	}

}
