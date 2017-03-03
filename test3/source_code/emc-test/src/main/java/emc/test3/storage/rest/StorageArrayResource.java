package emc.test3.storage.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import emc.test3.storage.service.StorageArrayService;
import net.sf.json.JSONObject;

@Path("api/v1.0")
public class StorageArrayResource {
	
	StorageArrayService service = new StorageArrayService();
	
	 // create a lun with id and size
	 // GET: localhost:8080/emc-test/api/v1.0/createlun?id=1&lunSize=100
	 @GET
	 @Path("createlun")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response createLUN(@QueryParam("id") long id, @QueryParam("lunSize") int lunSize) {
		 
		 Map<Long, Integer> idSizeMap = new HashMap<>();
		 idSizeMap.put(id, lunSize);
		 JSONObject result = service.createLUN(idSizeMap);
		 return Response.ok(result, MediaType.APPLICATION_JSON).build();
	 }
	 
	 // create multiple lun with id and size
	 @POST
	 @Path("createluns")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response createLUNs(JSONObject jsonObj) {
		 Map<Long, Integer> idSizeMap = jsonObj;
		 JSONObject result = service.createLUN(idSizeMap);
		 return Response.ok(result, MediaType.APPLICATION_JSON).build();
	 }
	 
	 
	 // resize a lun with id an size
	 // GET: localhost:8080/emc-test/api/v1.0/resizelun?id=1&lunSize=200
	 @GET
     @Path("resizelun")
	 @Produces(MediaType.APPLICATION_JSON)
     public Response resizeLUN(@QueryParam("id") long id, @QueryParam("lunSize") int lunSize) {
		 JSONObject result = service.resize(id, lunSize);
		 return Response.ok(result, MediaType.APPLICATION_JSON).build();
     }
	 
	 
	 // remove a lun with lun id
	 // GET: localhost:8080/emc-test/api/v1.0/removelun?id=1
	 @GET
     @Path("removelun")
	 @Produces(MediaType.APPLICATION_JSON)
     public Response removeLUN(@QueryParam("id") long id) {
		 JSONObject result = service.removeLUN(id);
		 return Response.ok(result, MediaType.APPLICATION_JSON).build();
     }
	 
	 // export a lun to a host
	 // step1: GET: localhost:8080/emc-test/api/v1.0/createlun?id=1&lunSize=200
	 // step2: GET: localhost:8080/emc-test/api/v1.0/export?id=1&hostName=bej102030.us.emc.com
	 @GET
     @Path("export")
	 @Produces(MediaType.APPLICATION_JSON)
     public Response export(@QueryParam("id") long id,
                     @QueryParam("hostName") String hostName) {
		 JSONObject result = service.exportToHost(id, hostName);
		 return Response.ok(result, MediaType.APPLICATION_JSON).build();
     }
	 
	 //Un-export a lun
	 // GET: localhost:8080/emc-test/api/v1.0/unexport?id=1
	 @GET
     @Path("unexport")
	 @Produces(MediaType.APPLICATION_JSON)
     public Response unExport(@QueryParam("id") long id) {
		 JSONObject result = service.unExport(id);
		 return Response.ok(result, MediaType.APPLICATION_JSON).build();
     }
	 

	 // retrieve information of a lun with id 
	 // GET: localhost:8080/emc-test/api/v1.0/retrieve?id=1
	 @GET
     @Path("retrieve")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.MULTIPART_FORM_DATA)
     public Response retrieve(@QueryParam("id") long id) {
		 JSONObject result = service.retrieveInfo(id);
		 return Response.ok(result, MediaType.APPLICATION_JSON).build();
     }
	 
	 
	 // persist a lun to database
	 // here dump it to a file instead  
	 // GET: localhost:8080/emc-test/api/v1.0/persist
	 @GET
     @Path("persist")
	 @Produces(MediaType.APPLICATION_JSON)
     public Response persist() {
		 JSONObject result = service.persist();
		 return Response.ok(result, MediaType.APPLICATION_JSON).build();
     }
	 

}
