package com.example.catlearn.catlearn.api;

import java.io.Serializable;

public class UploadFileResource implements Serializable {

	/*
	 * UploadFileUtils utils = new UploadFileUtils();
	 * 
	 * @POST
	 * 
	 * @Path("/image/{code}")
	 * 
	 * @Consumes(MediaType.MULTIPART_FORM_DATA)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response uploadImage(
	 * 
	 * @PathParam("code") String code,
	 * 
	 * @FormDataParam("file") InputStream fileInputStream){
	 * 
	 * 
	 * 
	 * System.out.println(fileInputStream.toString());
	 * 
	 * 
	 * Functions.criarDiretorio(ContextUtils.getInstance().
	 * getPathFilesAttachedsCatLearnCourse() + code);
	 * 
	 * String newFileName =
	 * ContextUtils.getInstance().getPathFilesAttachedsCatLearnCourse() + code +
	 * File.separator + "imagem_capa_course" + ".jpg";
	 * 
	 * // save it utils.writeToFile(fileInputStream, newFileName);
	 * 
	 * 
	 * String output = "File uploaded to : " + newFileName;
	 * System.out.println(output);
	 * 
	 * return Response.status(Response.Status.ACCEPTED).build(); }
	 * 
	 * @POST
	 * 
	 * @Path("/lessonfile/{codeCourse}/{codeModule}/{codeLesson}/{fileName}")
	 * 
	 * @Consumes(MediaType.MULTIPART_FORM_DATA)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response uploadContentLesson(
	 * 
	 * @PathParam("codeCourse") String codeCourse,
	 * 
	 * @PathParam("codeModule") String codeModule,
	 * 
	 * @PathParam("codeLesson") String codeLesson,
	 * 
	 * @PathParam("fileName") String fileName,
	 * 
	 * @FormDataParam("file") InputStream fileInputStream){
	 * 
	 * 
	 * 
	 * 
	 * System.out.println(fileInputStream.toString());
	 * 
	 * String url = codeCourse + File.separator + codeModule + File.separator +
	 * codeLesson ; Functions.criarDiretorio(ContextUtils.getInstance().
	 * getPathFilesAttachedsCatLearnCourse() + url);
	 * 
	 * String newFileName =
	 * ContextUtils.getInstance().getPathFilesAttachedsCatLearnCourse() + url +
	 * File.separator + fileName;
	 * 
	 * // save it utils.writeToFile(fileInputStream, newFileName);
	 * 
	 * 
	 * String output = "File uploaded to : " + newFileName;
	 * System.out.println(output);
	 * 
	 * return Response.status(Response.Status.ACCEPTED).build(); }
	 * 
	 */
}
