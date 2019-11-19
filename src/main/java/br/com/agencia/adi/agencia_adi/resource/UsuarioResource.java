package br.com.agencia.adi.agencia_adi.resource;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.agencia.adi.agencia_adi.dao.UsuarioDAO;
import br.com.agencia.adi.agencia_adi.model.UsuarioModel;
import br.edu.ifsp.app.User;

@Path("usuarioresource")
public class UsuarioResource {

	UsuarioDAO dao = new UsuarioDAO();
	
	@GET
	@Path("user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioModel ObterCliente(@PathParam("id") int id) {
		UsuarioDAO dao = new UsuarioDAO();
		UsuarioModel usuario = dao.ObterCliente(id);
		return usuario;
	}
	
//	@GET
//	@Path("user/{id}")
//	@Produces(MediaType.APPLICATION_XML)
//	public User getUser(@PathParam("id") int id) {
//		User user = dao.getUser(id);
//		return user;
//	}
//
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allusers")
	public List<UsuarioModel> getUsers() {
		System.out.println("no user/resource/all");
		UsuarioDAO dao = new UsuarioDAO(); 
		List<UsuarioModel> lista = dao.ListarCliente();
		return lista;
	}
	
	@POST
	@Path("/user")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioModel CadastrarCliente(UsuarioModel usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		return dao.CadastrarCliente(usuario);
	}
	
//	@PUT
//	@Path("/user")
//	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
//	public User updateUser(User user) {
//		User updateUser = userRepository.update(user);
//		return updateUser;
//	}
//	
//	@DELETE
//	@Path("/user/{id}")
//	public String deleteUser(@PathParam("id") int id) {
//		User user = userRepository.getUser(id);
//		
//		if (userRepository.delete(id)) {
//			return "User " + user.getName() + " deleted.";
//		} else {
//			return "User not found.";		
//		}
//	}
}
