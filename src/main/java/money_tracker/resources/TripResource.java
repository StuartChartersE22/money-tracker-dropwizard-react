package money_tracker.resources;

import io.dropwizard.jersey.params.IntParam;
import money_tracker.api.Trip;
import money_tracker.core.repos.TripRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("trips")
@Produces(MediaType.APPLICATION_JSON)
public class TripResource {

    @GET
    public List<Trip> allTransactions(){
        return TripRepo.getAll();
    }

    @GET
    @Path("{id}")
    public Trip getTransaction(@PathParam("id") IntParam id){
        return TripRepo.find(id.get());
    }

    @POST
    public void create(Trip trip){
        TripRepo.save(trip);
    }

    @PUT
    @Path("{id}")
    public void update(@PathParam("id") IntParam id){
        Trip trip = TripRepo.find(id.get());
        TripRepo.update(trip);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") IntParam id){
        Trip trip = TripRepo.find(id.get());
        TripRepo.delete(trip);
    }
}
