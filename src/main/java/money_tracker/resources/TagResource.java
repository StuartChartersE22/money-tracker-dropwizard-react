package money_tracker.resources;

import io.dropwizard.jersey.params.IntParam;
import money_tracker.api.Tag;
import money_tracker.core.repos.TagRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("tags")
@Produces(MediaType.APPLICATION_JSON)
public class TagResource {

    @GET
    public List<Tag> allTags(){
        return TagRepo.getAll();
    }

    @GET
    @Path("{id}")
    public Tag getTag(@PathParam("id") IntParam id){
        return TagRepo.find(id.get());
    }

    @POST
    public void create(Tag tag){
        TagRepo.save(tag);
    }

    @PUT
    @Path("{id}")
    public void update(@PathParam("id") IntParam id){
        Tag tag = TagRepo.find(id.get());
        TagRepo.update(tag);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") IntParam id){
        Tag tag = TagRepo.find(id.get());
        TagRepo.delete(tag);
    }
}
