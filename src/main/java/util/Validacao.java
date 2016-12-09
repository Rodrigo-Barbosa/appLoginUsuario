package util;

import java.time.LocalDate;
import java.time.Month;

import model.Usuario;

public class Validacao {
	static DAOUsuario dao = new DAOUsuario();
		
	public static void main(String[] args) {
	Usuario usuario = new Usuario("Rodrigo", "05068476919", "rodrigobarbos.rb", "123", LocalDate.of(1988, Month.FEBRUARY, 04));
		//usuario.setNome("Rodrigo");
		//usuario.setCpf("05068476919");
		//usuario.setEmail("rodrigobarbos.rb");
		//usuario.setSenha("123");
		//usuario.setDataNascimento();
		dao.inserir(usuario);
		System.out.println(dao.listarUsuarios().size());
		System.out.println(verificarUsuario(usuario.getEmail(), "123"));
	}
	
	public static boolean verificarUsuario(String email, String senha){
		return dao.listarUsuarios().stream().anyMatch((obj)
				-> obj.getSenha().equals(senha) && obj.getEmail().equals(email));
	}
}
