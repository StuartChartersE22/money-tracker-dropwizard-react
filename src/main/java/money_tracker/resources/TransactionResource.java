package money_tracker.resources;

import io.dropwizard.jersey.params.IntParam;
import money_tracker.api.Transaction;
import money_tracker.core.repos.TransactionRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("transaction")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionResource {

    @GET
    public List<Transaction> allTransactions(){
        return TransactionRepo.getAll();
    }

    @GET
    @Path("{id}")
    public Transaction getTransaction(@PathParam("id") IntParam id){
        return TransactionRepo.find(id.get());
    }

    @POST
    public void create(Transaction transaction){
        TransactionRepo.save(transaction);
    }

    @PUT
    @Path("{id}")
    public void update(@PathParam("id") IntParam id){
        Transaction transaction = TransactionRepo.find(id.get());
        TransactionRepo.update(transaction);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") IntParam id){
        Transaction transaction = TransactionRepo.find(id.get());
        TransactionRepo.delete(transaction);
    }
}
