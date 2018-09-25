package money_tracker.core.repos;

import money_tracker.api.Person;
import money_tracker.api.Tag;
import money_tracker.api.Transaction;
import money_tracker.api.Trip;
import money_tracker.core.DBHelper;

import java.util.List;

public class TransactionRepo extends DBHelper {

    public static void deleteAll(){
        deleteAll(Transaction.class);
    }

    public static List<Transaction> getAll(){
        return getAll(Transaction.class);
    }

    public static Transaction find(int id){
        return find(id, Transaction.class);
    }

    public static List<Person> getLendings(Transaction transaction){
        return getAssociationsForAnObject(transaction, Person.class, "lendings");
    }

    public static List<Tag> getTags(Transaction transaction){
        return getAssociationsForAnObject(transaction, Tag.class, "tags");
    }

    public static Trip getTrip(Transaction transaction){
        return getAnAssociationForAnObject(transaction, Trip.class, "trip");
    }


}