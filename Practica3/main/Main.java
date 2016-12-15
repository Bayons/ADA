package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import usuario.User;

public class Main {

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

	public static User getUserPorNombre(ArrayList<User> usuarios, String nombre) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getNombre().equals(nombre))
				return usuarios.get(i);
		}
		return null;
	}

	public static void buscaComunidades(ArrayList<User> usuarios) {
		FileWriter fich = null;
		int j, k;
		try {
			fich = new FileWriter("salida_p3_migbayo_danpare.txt");

			boolean[] hasComunidad = new boolean[usuarios.size()];
			ArrayList<User> comunidad;

			for (int i = 0; i < usuarios.size(); i++) {
				if (!hasComunidad[i] && (comunidad = getComunidades(usuarios.get(i))) != null) {
					fich.write("COMUNIDAD:\r\n");
					for (k = 0; k < comunidad.size(); k++){
						fich.write(comunidad.get(k).getNombre()+" ");
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
