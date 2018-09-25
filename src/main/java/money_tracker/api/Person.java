package money_tracker.api;

import money_tracker.api.behaviours.IDB;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Person implements IDB {

    private int id;
    private String name;
    private List<Transaction> borrowings;

    public Person() {}

    public Person(String name) {
        this.name = name;
        this.borrowings = new ArrayList<>();
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

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(
            name = "trans_people",
            joinColumns = {@JoinColumn (name = "person_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn (name = "trans_id", nullable = false)}
    )
    public List<Transaction> getBorrowings(){
        return this.borrowings;
    }

    public void setBorrowings(List<Transaction> borrowings) {
        this.borrowings = borrowings;
    }
}
