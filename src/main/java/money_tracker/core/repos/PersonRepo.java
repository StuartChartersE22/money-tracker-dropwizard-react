package money_tracker.core.repos;

import money_tracker.api.Person;
import money_tracker.api.Transaction;
import money_tracker.core.DBHelper;

import java.util.List;

public class PersonRepo extends DBHelper {

    public static void deleteAll(){
        deleteAll(Person.class);
    }

    public static List<Person> getAll(){
        return getAll(Person.class);
    }

    public static Person find (int id){
        return find(id, Person.class);
    }

    public static List<Transaction> getBorrowings(Person person){
        return getAssociationsForAnObject(person, Transaction.class, "lendings");
    }

}
