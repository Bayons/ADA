package usuario;

import java.util.ArrayList;

public class User {

	String nombre;
	ArrayList<User> siguiendo;

	public User(String nombre) {
		this.nombre = nombre;
		siguiendo = new ArrayList<User>();
	}

	public void sigueA(User user) {
		if (!siguiendo.contains(user))
			siguiendo.add(user);
	}

	public boolean puedeLeer(User user) {
		ArrayList<User> usuarios = aQuienVe();
		return usuarios.contains(user);
	}

	public ArrayList<User> aQuienVe() {
		ArrayList<User> usuarios = new ArrayList<User>();
		usuarios = aQuienVe(usuarios);
		
		if(!usuarios.contains(this)){
			usuarios.add(this);
		}
		//usuarios.remove(this);
		return usuarios;
	}

	private ArrayList<User> aQuienVe(ArrayList<User> usuarios) {
		for (int i = 0; i < siguiendo.size(); i++) {
			if (!usuarios.contains(siguiendo.get(i))) {
				usuarios.add(siguiendo.get(i));
				usuarios = siguiendo.get(i).aQuienVe(usuarios);
			}
		}
		return usuarios;
	}

	public ArrayList<User> getSiguiendo() {
		return siguiendo;
	}

	public void setSiguiendo(ArrayList<User> siguiendo) {
		this.siguiendo = siguiendo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof User && nombre.equals(((User) obj).getNombre())) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return nombre;
	}

}
