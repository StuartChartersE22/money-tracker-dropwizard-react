package money_tracker.api;

import money_tracker.api.behaviours.IDB;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "transactions")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "transaction_type")
public class Transaction implements IDB {
    private int id;
    private String name;
    private Trip trip;
    private int amount;
    private Calendar date;
    private boolean isBusiness;
    private List<Tag> tags;
    protected List<Person> lendings;

    public Transaction() {}

    public Transaction(String name, int amount, Calendar date, boolean isBusiness, Trip trip) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.isBusiness = isBusiness;
        this.tags = new ArrayList<>();
        this.lendings = new ArrayList<>();
        this.trip = trip;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Column (name = "date")
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Column(name = "business")
    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean business) {
        isBusiness = business;
    }

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(
            name = "trans_tags",
            joinColumns = {@JoinColumn (name = "trans_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn (name = "tag_id", nullable = false)}
    )
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(
            name = "trans_people",
            joinColumns = {@JoinColumn (name = "trans_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn (name = "person_id", nullable = false)}
    )
    public List<Person> getLendings() {
        return lendings;
    }
    public void setLendings(List<Person> lendings) {
        this.lendings = lendings;
    }
    public void addLending(Person person){
        this.lendings.add(person);
    }
}
