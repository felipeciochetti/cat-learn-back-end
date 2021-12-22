package api;


import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.catlearn.Module;
import repositorio.Modules;
import service.catlearn.ModuleService;

@Path("/module")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ModuleResource  implements Serializable{


	ModuleService service = new ModuleService();

	Modules repositorio = new Modules();



	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar() {

		List<Module> list =  service.listar();

		GenericEntity<List<Module>> myEntity = new GenericEntity<List<Module>>(list) {};


		return		Response.status(201).entity(myEntity).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		Module course = repositorio.get(id);
		if (course != null) {
			return Response.ok(course, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Module course ) {

		try{

			course = service.adicionar(course);
			return Response.ok(course, MediaType.APPLICATION_JSON).build();

		}catch(Exception e){
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}


	}


	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response update(@PathParam("id") int id, Module module ) {
		module.setId(id);

		try {
			service.update(module);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
		}

	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id) {

		String ret = service.excluir(id) ;

		if(ret.equals("")){
			return Response.ok().build();
		}else{
			return Response.status(Response.Status.FORBIDDEN).entity(ret).build();

		}

	}










}