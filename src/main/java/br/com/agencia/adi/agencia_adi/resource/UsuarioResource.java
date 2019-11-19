package br.com.agencia.adi.agencia_adi.resource;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.agencia.adi.agencia_adi.dao.UsuarioDAO;
import br.com.agencia.adi.agencia_adi.model.UsuarioModel;

@Path("usuarioresource")
public class UsuarioResource {

	UsuarioDAO dao = new UsuarioDAO();
	
	@GET
	@Path("user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioModel ObterCliente(@PathParam("id") int id) {
		return dao.ObterCliente(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allusers")
	public List<UsuarioModel> ListarCliente() {
		return dao.ListarCliente();
	}
	
	@POST
	@Path("/user")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioModel CadastrarCliente(UsuarioModel usuario) {
		return dao.CadastrarCliente(usuario);
	}
	
	@PUT
	@Path("/user")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioModel EditarCliente(UsuarioModel usuario) {
		return dao.EditarCliente(usuario);
	}
	
	@DELETE
	@Path("/user/{id}")
	public Boolean DeletarCliente(@PathParam("id") int id) {
		return dao.DeletarCliente(id);
	}
}
