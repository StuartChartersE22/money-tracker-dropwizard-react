package money_tracker.resources;

import io.dropwizard.jersey.params.IntParam;
import money_tracker.api.Person;
import money_tracker.core.repos.PersonRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("people")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    public List<Person> allPeople(){
        return PersonRepo.getAll();
    }

    @GET
    @Path("{id}")
    public Person getPerson(@PathParam("id") IntParam id){
        return PersonRepo.find(id.get());
    }

    @POST
    public void create(Person person){
        PersonRepo.save(person);
    }

    @PUT
    @Path("{id}")
    public void update(@PathParam("id") IntParam id){
        Person person = PersonRepo.find(id.get());
        PersonRepo.update(person);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") IntParam id){
        Person person = PersonRepo.find(id.get());
        PersonRepo.delete(person);
    }
}
