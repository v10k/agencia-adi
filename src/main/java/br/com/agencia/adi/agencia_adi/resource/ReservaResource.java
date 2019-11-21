package br.com.agencia.adi.agencia_adi.resource;

import java.sql.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.agencia.adi.agencia_adi.Seguro;
import br.com.agencia.adi.agencia_adi.dao.ReservaDAO;
import br.com.agencia.adi.agencia_adi.model.NivelPermissao;
import br.com.agencia.adi.agencia_adi.model.ReservaModel;

@Path("reservaresource")
public class ReservaResource {

	ReservaDAO dao = new ReservaDAO();
	
	@POST
	@Seguro({NivelPermissao.USUARIO})
	@Path("/requestroom")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_JSON)
	public ReservaModel AgendarSala(ReservaModel reserva) {
		return dao.AgendarSala(reserva);
	}
	
	@GET
	@Seguro
	@Path("requestroom/{data}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReservaModel> ListarSalas(@PathParam("data") String data) {
		return dao.ConsultarAgendamento(data);
	}
	
	
	@GET
	@Seguro
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allrequests")
	public List<ReservaModel> getRequests() {
		return dao.ListarReservas();
	}
	
	@GET
	@Seguro({NivelPermissao.USUARIO})
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userrequests/{id}")
	public List<ReservaModel> getUserRequests(@PathParam("id") int id) {
		return dao.ListarReservas(id);
	}	

	@DELETE
	@Seguro({NivelPermissao.ADM, NivelPermissao.USUARIO})
	@Path("/requestroom/{id}/{data}")
	public Response CancelarAgendamento(@PathParam("id") int id, @PathParam("data") Date data_reserva) {
		if (dao.CancelarAgendamento(id, data_reserva)) {
			return Response.ok("Sala deletada com sucesso").build();
		} 
		return Response.status(Response.Status.NOT_FOUND).entity("Não foi possível deletar sala").build();	
	}
}
