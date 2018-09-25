package money_tracker.api;

import money_tracker.api.behaviours.IDB;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Tag implements IDB {

    private int id;
    private String name;
    private List<Transaction> transactions;

    public Tag(String name) {
        this.name = name;
        this.transactions = new ArrayList<>();
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
            name = "trans_tags",
            joinColumns = {@JoinColumn (name = "tag_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn (name = "trans_id", nullable = false)}
    )
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
