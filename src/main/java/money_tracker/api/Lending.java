package money_tracker.api;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "lendings")
public class Lending extends Transaction {

    public Lending(Person person, int amount, Calendar date, boolean isBusiness, Trip trip) {
        super("Lent to " + person.getName(), amount, date, isBusiness, trip);
        List<Person> lent = new ArrayList<>();
        lent.add(person);
        this.setLendings(lent);
    }

}
