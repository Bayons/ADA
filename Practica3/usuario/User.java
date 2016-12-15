package usuario;

import java.util.ArrayList;

/**
 * Clase User
 *
 * Contiene la informacion de cada usuario
 *
 * @author Miguel Bayon Sanz
 * @author Daniel Paredes Santamaria
 * @version 1.0
 */
public class User {

	String nombre;
	ArrayList<User> siguiendo;

	/**
	 * Constructor con un parametro
	 * 
	 * @param nombre
	 *            nombre del usuario
	 */
	public User(String nombre) {
		this.nombre = nombre;
		siguiendo = new ArrayList<User>();
	}

	/**
	 * Hace que este usuario siga a user
	 * 
	 * @param user
	 *            usuario que es seguido
	 */
	public void sigueA(User user) {
		if (!siguiendo.contains(user) && !user.equals(this))
			siguiendo.add(user);
	}

	/**
	 * Ve si el usuario puede leer a user
	 * 
	 * @param user
	 *            usuario del que puede leer
	 * @return true si puede leer o false en caso contrario
	 */
	public boolean puedeLeer(User user) {
		ArrayList<User> usuarios = aQuienVe();
		return usuarios.contains(user);
	}

	/**
	 * Saca una lista de todos los usuarios a los que puede ver este usuario
	 * 
	 * @return usuarios ArrayList de usuarios a los que ve
	 */
	public ArrayList<User> aQuienVe() {
		ArrayList<User> usuarios = new ArrayList<User>();
		usuarios = aQuienVe(usuarios);

		if (!usuarios.contains(this))
			usuarios.add(this);
		return usuarios;
	}

	/**
	 * Saca una lista de todos los usuarios a los que puede ver este usuario
	 * 
	 * @param usuarios
	 *            ArrayList de usuarios
	 * @return usuarios ArrayList de usuarios a los que ve
	 */
	private ArrayList<User> aQuienVe(ArrayList<User> usuarios) {
		for (int i = 0; i < siguiendo.size(); i++) {
			if (!usuarios.contains(siguiendo.get(i))) {
				usuarios.add(siguiendo.get(i));
				usuarios = siguiendo.get(i).aQuienVe(usuarios);
			}
		}
		return usuarios;
	}

	/**
	 * Getter de siguiendo
	 * 
	 * @return siguiendo ArrayList de usuarios
	 */
	public ArrayList<User> getSiguiendo() {
		return siguiendo;
	}

	/**
	 * Setter de siguiendo
	 * 
	 * @param siguiendo
	 *            ArrayList de usuarios
	 */
	public void setSiguiendo(ArrayList<User> siguiendo) {
		this.siguiendo = siguiendo;
	}

	/**
	 * Getter del nombre
	 * 
	 * @return nombre String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter del nombre
	 * 
	 * @param nombre
	 *            nombre del usuario
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Override del metodo equal de la clase Object
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof User && nombre.equals(((User) obj).getNombre())) {
			return true;
		}
		return false;
	}

	/**
	 * Override del metodo toString de la clase Object
	 */
	@Override
	public String toString() {
		return nombre;
	}

}
