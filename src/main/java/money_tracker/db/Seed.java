package money_tracker.db;

import money_tracker.api.Person;
import money_tracker.api.Tag;
import money_tracker.api.Transaction;
import money_tracker.api.Trip;
import money_tracker.core.DBHelper;
import money_tracker.core.repos.PersonRepo;
import money_tracker.core.repos.TagRepo;
import money_tracker.core.repos.TransactionRepo;
import money_tracker.core.repos.TripRepo;

import java.util.ArrayList;
import java.util.Calendar;

public class Seed {
    public static void seedData() {
        TransactionRepo.deleteAll();
        TagRepo.deleteAll();
        PersonRepo.deleteAll();
        TripRepo.deleteAll();

        Tag tag1 = new Tag("Holiday");
        Tag tag2 = new Tag("Travel");
        Tag tag3 = new Tag("Lunch");
        Tag tag4 = new Tag("Drink");

        DBHelper.save(tag1);
        DBHelper.save(tag2);
        DBHelper.save(tag3);
        DBHelper.save(tag4);

        Person person1 = new Person("Stuart");
        Person person2 = new Person("Catriona");
        Person person3 = new Person("Heather");
        Person person4 = new Person("Derek");

        DBHelper.save(person1);
        DBHelper.save(person2);
        DBHelper.save(person3);
        DBHelper.save(person4);

        Trip trip1 = new Trip("Cycling", 6300, true, false, Calendar.getInstance());

        DBHelper.save(trip1);

        Transaction transaction1 = new Transaction("Lunch", 2000, Calendar.getInstance(), false, trip1);

        DBHelper.save(transaction1);

        transaction1.addLending(person1);
        transaction1.addTag(tag3);

        DBHelper.update(transaction1);
    }
}
