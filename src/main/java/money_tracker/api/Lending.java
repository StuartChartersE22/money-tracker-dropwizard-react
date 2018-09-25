package money_tracker.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Lending extends Transaction {

    public Lending(Person person, float amount, Calendar date, boolean isBusiness, Trip trip) {
        super("Lent to " + person.getName(), amount, date, isBusiness, trip);
        List<Person> lent = new ArrayList<>();
        lent.add(person);
        this.setBorrowings(lent);
    }

}
