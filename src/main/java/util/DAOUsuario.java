package util;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class DAOUsuario {
	private static List<Usuario> todosUsuarios = new ArrayList<Usuario>();
	
	public List<Usuario> listarUsuarios() {
		return todosUsuarios;
	}

	public boolean inserir(Usuario user) {
		this.todosUsuarios.add(user);
		
		System.out.println("Usuario adicionado na Lista");
		System.out.println("Tamanho da lista " + todosUsuarios.size());
		return true;
	}	

	public Usuario buscaUsuarioPorEmail(String email) {
		List<Usuario> emailUsuario = new ArrayList<>();
		todosUsuarios.forEach(n -> {
			if (n.getEmail() != null && n.getEmail().equalsIgnoreCase(email)) {
				emailUsuario.add(n);
			}
		});
		return emailUsuario.get(0);
	}
}
