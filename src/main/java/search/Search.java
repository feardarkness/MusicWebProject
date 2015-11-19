/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import org.apache.lucene.queryparser.classic.ParseException;

/**
 *
 * @author aalvarado
 */
@Stateless
@ApplicationPath("msod")        // allow rest to work. ) the class should extend Applicacion too
@Path("search")
public class Search extends Application {

    @Inject
    LocalIndexSearcher indexSearcher;

    public Search() {
    }

    @GET
    @Path("{words}")
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResponse BasicSearch(@PathParam("words") String words) {
        SearchResponse searchResponse = null;
        if (words.length() < 100) {
            try {
                searchResponse = indexSearcher.search(words);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        } else {
            searchResponse = SearchResponse.createErrorResponse();
        }
        return searchResponse;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String save() {
        return "Hello World 2";
    }

}
