package eu.scape_project.watch.core.rest.resource;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sun.jersey.spi.resource.Singleton;
import com.wordnik.swagger.core.Api;

@Path("/entitytype.json")
@Api(value = "/entitytype", description = "Operations about Entity Types")
@Singleton
@Produces({"application/json"})
public class EntityTypeResourceJSON extends EntityTypeResource {

}
