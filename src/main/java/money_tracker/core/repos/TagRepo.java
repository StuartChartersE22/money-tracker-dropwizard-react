package money_tracker.core.repos;

import money_tracker.api.Tag;
import money_tracker.api.Transaction;
import money_tracker.core.DBHelper;

import java.util.List;

public class TagRepo extends DBHelper {

    public static void deleteAll(){
        deleteAll(Tag.class);
    }

    public static List<Tag> getAll(){
        return getAll(Tag.class);
    }

    public static Tag find(int id){
        return find(id, Tag.class);
    }

    public static List<Transaction> getTransactions(Tag tag){
        return getAssociationsForAnObject(tag, Transaction.class, "transactions");
    }

}
