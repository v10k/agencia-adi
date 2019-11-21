package br.com.agencia.adi.agencia_adi.resource;

import java.util.List;


import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.agencia.adi.agencia_adi.Seguro;
import br.com.agencia.adi.agencia_adi.dao.AdmDAO;
import br.com.agencia.adi.agencia_adi.dao.ClienteDAO;
import br.com.agencia.adi.agencia_adi.dao.IUsuario;
import br.com.agencia.adi.agencia_adi.dao.UsuarioDAO;
import br.com.agencia.adi.agencia_adi.model.NivelPermissao;
import br.com.agencia.adi.agencia_adi.model.UsuarioModel;

@Path("usuarioresource")
public class UsuarioResource {

	UsuarioDAO dao = new UsuarioDAO();
	IUsuario tipo = new ClienteDAO();
	
	@GET
	@Seguro
	@Path("user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioModel ObterCliente(@PathParam("id") int id) {
		dao.Iusuario = tipo;
		return dao.ObterCliente(id);
	}
	
	@Seguro({NivelPermissao.ADM})
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allusers")
	public List<UsuarioModel> ListarCliente() {
		tipo = new AdmDAO();
		dao.Iusuario = tipo;
		return dao.ListarCliente();
	}
	
	@POST
	@Path("/user")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioModel CadastrarCliente(UsuarioModel usuario) {
		dao.Iusuario = tipo;
		return dao.CadastrarCliente(usuario);
	}
	
	@PUT
	@Path("/user")
	@Seguro
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioModel EditarCliente(UsuarioModel usuario) {
		dao.Iusuario = tipo;
		return dao.EditarCliente(usuario);
	}
	
	@Seguro({NivelPermissao.ADM})
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user/{id}")
	public Response DeletarCliente(@PathParam("id") int id) {
		tipo = new AdmDAO();
		dao.Iusuario = tipo;
		if (dao.DeletarCliente(id)) {
			return Response.ok("Deletado com sucesso").build();
		} 
		return Response.status(Response.Status.NOT_FOUND).entity("Não foi possível deletar usuário").build();			
	}
	

	@POST
	@Path("user/authentication")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fazerLogin(UsuarioModel usuario){
		dao.Iusuario = tipo;
		try {
			if (dao.Login(usuario) == true) {
				String token = dao.gerarToken(usuario.getEmail(), 1);
				usuario = dao.ObterCliente(usuario.getEmail());
				String json = Json.createObjectBuilder().add("token", token).add("id_usuario", usuario.getId_user()).build().toString();
				return Response.ok(json).build();
			} else {
				return Response.status(Status.UNAUTHORIZED).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.UNAUTHORIZED).build();
		}
	}
}
