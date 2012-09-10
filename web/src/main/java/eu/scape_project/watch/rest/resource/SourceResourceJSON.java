package eu.scape_project.watch.rest.resource;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sun.jersey.spi.resource.Singleton;
import com.wordnik.swagger.core.Api;

/**
 * {@link SourceResource} with JSON output.
 * 
 * @author Luis Faria <lfaria@keep.pt>
 * 
 */
@Path("/source.json")
@Api(value = "/source", description = "Operations about Sources")
@Singleton
@Produces({"application/json"})
public class SourceResourceJSON extends SourceResource {

}
