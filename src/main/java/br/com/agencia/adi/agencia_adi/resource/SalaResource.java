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

import br.com.agencia.adi.agencia_adi.Seguro;
import br.com.agencia.adi.agencia_adi.dao.SalaDAO;
import br.com.agencia.adi.agencia_adi.model.NivelPermissao;
import br.com.agencia.adi.agencia_adi.model.SalaModel;

@Path("salaresource")
public class SalaResource {

	SalaDAO dao = new SalaDAO();
	
	@POST
	@Seguro({NivelPermissao.ADM})
	@Path("/room")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public SalaModel CadastrarSala(SalaModel sala) {
		return dao.CadastrarSala(sala);
	}
	
	@GET
	@Path("room/{id}")
	@Seguro
	@Produces(MediaType.APPLICATION_JSON)
	public SalaModel ObterSala(@PathParam("id") int id) {
		return dao.ObterSala(id);
	}
	
	@GET
	@Seguro
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allrooms")
	public List<SalaModel> getRooms() {
		return dao.ListarSalas();
	}
	
	@GET
	@Seguro
	@Path("roomsearch/{data}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SalaModel> ListarSalas(@PathParam("data") String data) {
		return dao.ListarSalas(data);
	}
	
	@PUT
	@Seguro({NivelPermissao.ADM})
	@Path("/room")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public SalaModel EditarSala(SalaModel sala) {
		return dao.EditarSala(sala);
	}
	
	@DELETE
	@Seguro({NivelPermissao.ADM})
	@Path("/room/{id}")
	public Boolean DeletarSala(@PathParam("id") int id) {
		return dao.DeletarSala(id);
	}
}
