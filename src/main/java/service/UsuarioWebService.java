package service;

import static util.ClasseJson.gerarJson;
import static util.Validacao.verificarUsuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import model.Usuario;
import util.DAOUsuario;

@Path("usuario/")
@ApplicationPath("rest/")
public class UsuarioWebService extends Application {
	DAOUsuario dao = new DAOUsuario();
	Usuario usuario;

	public UsuarioWebService() {
		usuario = new Usuario();
	}
	
	@GET
	@Path("login/{email}/{senha}")
	public Response acessoAplicacao(@PathParam("email") String email, @PathParam("senha") String senha) {
		if (verificarUsuario(email, senha)) {
			return Response.status(Response.Status.OK).entity(gerarJson(dao.buscaUsuarioPorEmail(email)))
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity(Response.Status.UNAUTHORIZED.toString())
					.header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
	@GET
	@Path("/cadastrar/{nome}/{email}/{cpf}/{senha}/{datanascimento}")
	public Response cadastroUsuario(@PathParam("nome") String nome, @PathParam("email") String email,
			@PathParam("cpf") String cpf, @PathParam("senha") String senha,
			@PathParam("datanascimento") String dataNascimento) {
		try {
			String dataStr[] = dataNascimento.split("-");
			LocalDate dataToLocalDate = LocalDate.of(Integer.valueOf(dataStr[0]), Integer.valueOf(dataStr[1]),
					Integer.valueOf(dataStr[2]));
			Usuario usuario = new Usuario(nome, cpf, email, senha, dataToLocalDate);
			dao.inserir(usuario);
			return Response.status(Response.Status.OK).entity(gerarJson(usuario))
					.header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(Response.Status.BAD_REQUEST.toString())
					.header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@GET
	@Path("consulta/{dado}/{tipo}")
	public Response usuarios(@PathParam("dado") String dado, @PathParam("tipo") Integer tipo) {
		List<Usuario> usuarios = dao.listarUsuarios();
		List<Usuario> retorno = new ArrayList<>();

		if (tipo == 1) {
			usuarios.forEach(n -> {
				if (n.getNome() != null && n.getNome().equalsIgnoreCase(dado)) {
					retorno.add(n);
				}
			});
		}
		if (tipo == 2) {
			usuarios.forEach(n -> {
				if (n.getDataNascimento().equals(dado)) {
					retorno.add(n);
				}
			});
		} else if (tipo == 3) {
			usuarios.forEach(n -> {
				if (n.getEmail() != null && n.getEmail().equalsIgnoreCase(dado)) {
					retorno.add(n);
				}
			});
		} else if (tipo == 4) {
			usuarios.forEach(n -> {
				if (n.getCpf() != null && n.getCpf().equalsIgnoreCase(dado)) {
					retorno.add(n);
				}
			});
		}
		return Response.status(Response.Status.OK).entity(gerarJson(retorno)).header("Access-Control-Allow-Origin", "*")
				.build();
	}
}
