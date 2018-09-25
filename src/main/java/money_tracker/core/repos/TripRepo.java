package money_tracker.core.repos;

import money_tracker.api.Transaction;
import money_tracker.api.Trip;
import money_tracker.core.DBHelper;

import java.util.List;

public class TripRepo extends DBHelper {

    public static void deleteAll(){
        deleteAll(Trip.class);
    }

    public static List<Trip> getAll(){
        return getAll(Trip.class);
    }

    public static Trip find(int id){
        return find(id, Trip.class);
    }

    public static List<Transaction> getTransactions(Trip trip){
        return getAssociationsForAnObject(trip, Transaction.class, "transactions");
    }

}
