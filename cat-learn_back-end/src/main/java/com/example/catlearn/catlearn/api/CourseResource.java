package com.example.catlearn.catlearn.api;


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

import model.catlearn.Course;
import repositorio.Courses;
import service.catlearn.CourseService;

@Path("/course")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource  implements Serializable{


	CourseService service = new CourseService();

	Courses repositorio = new Courses();



	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar() {

		List<Course> list =  service.listar();

		GenericEntity<List<Course>> myEntity = new GenericEntity<List<Course>>(list) {};


		return		Response.status(201).entity(myEntity).build();
	}

	
	
	
	
	@GET
	@Path("/search/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("name") String name) {
		
		//commint Mac :)
		
		List<Course> list =		service.searchByName(name);		
		
		
		if(list.isEmpty()){
			list = repositorio.getTodosCoursees();
		}

		GenericEntity<List<Course>> myEntity = new GenericEntity<List<Course>>(list) {};

		return		Response.status(201).entity(myEntity).build();


	}
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		Course course = repositorio.get(id);
		if (course != null) {
			return Response.ok(course, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Course course ) {

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
	public Response update(@PathParam("id") int id, Course course ) {
		course.setId(id);
		try {
			service.update(course);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
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