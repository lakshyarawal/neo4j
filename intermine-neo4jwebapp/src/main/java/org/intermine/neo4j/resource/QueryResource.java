package org.intermine.neo4j.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.intermine.neo4j.resource.bean.QueryResultBean;
import org.intermine.neo4j.service.Neo4jQueryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * @author Yash Sharma
 */
@Path("query")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Api(value = "Query")
public class QueryResource {

    Neo4jQueryService neo4jQueryService = new Neo4jQueryService();

    @GET
    @Path("results")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Query InterMine Neo4j Graph with a Path Query.",
                notes = "API is currently in development. Report any issues at https://github.com/intermine/neo4j/issues.")
    public String getResults(@BeanParam QueryResultBean bean) throws Exception {
        String pathQuery = bean.getPathQuery();
        if (pathQuery == null) {
            throw new BadRequestException("Invalid Path Query.");
        }
        if (bean.getSize() < 0) {
            throw new BadRequestException("Invalid size parameter: " + String.valueOf(bean.getSize()));
        }
        if (bean.getStart() < 0) {
            throw new BadRequestException("Invalid start parameter: " + String.valueOf(bean.getStart()));
        }
        return neo4jQueryService.getQueryResult(bean).toJSON().toString();
    }

    @GET
    @Path("cypher")
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(value = "Convert a PathQuery to Cypher.",
            notes = "API is currently in development. Report any issues at https://github.com/intermine/neo4j/issues.")
    public String getCypher(@QueryParam("query") String pathQuery) throws Exception {
        if (pathQuery == null) {
            throw new BadRequestException("Invalid Path Query");
        }
        return neo4jQueryService.getCypherQuery(pathQuery).toString();
    }
}
