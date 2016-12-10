package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import usuario.User;

public class Main {

	public static void main(String[] args) {
		String cadena;
		String[] linea;
		ArrayList<User> usuarios = new ArrayList<User>();
		User user1 = null, user2 = null;
		boolean[][] mAdy;

		// Creacion de usuarios
		try {
			FileReader fr = new FileReader("entrada.txt");
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
			// mAdy[i][j]-> i sigue a j

			for (int i = 0; i < usuarios.size(); i++) {
				for (int j = 0; j < usuarios.size(); j++) {
					if (usuarios.get(i).puedeLeer(usuarios.get(j)))
						mAdy[i][j] = true;
					else
						mAdy[i][j] = false;
				}
			}

			System.out.println("   A B G C H F D E I J\n  ---------------------");
			for (int i = 0; i < usuarios.size(); i++) {
				System.out.print(usuarios.get(i)+"| ");
				for (int j = 0; j < usuarios.size(); j++) {
					if (mAdy[i][j]) {
						System.out.print("X|");
					} else {
						System.out.print(" |");
					}
				}
				System.out.println();
			}

			
			
			
			
			
			
			
			
			
			
			for (int i = 0; i < usuarios.size(); i++) {
				System.out.println("--> " + usuarios.get(i));
				for (int j = 0; j < usuarios.get(i).getSiguiendo().size(); j++) {
					System.out.println(usuarios.get(i).getSiguiendo().get(j));
				}
				System.out.println();
			}

			System.out.println("----------------------\n");

			ArrayList<User> nuevo = getUserPorNombre(usuarios, "A").aQuienVe();
			for (int i = 0; i < nuevo.size(); i++) {
				System.out.println(nuevo.get(i));
			}

			br.close();
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Un grafo vacio es un grafo ;)
		// Lee usuarios:
		// usuarios.add
	}

	public static User getUserPorNombre(ArrayList<User> usuarios, String nombre) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getNombre().equals(nombre))
				return usuarios.get(i);
		}
		return null;
	}

}
