package money_tracker.api;

import money_tracker.api.behaviours.IDB;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "trips")
public class Trip implements IDB {
    private int id;
    private String name;
    private int budget;
    private boolean isCurrent;
    private boolean isBusiness;
    private Calendar date;
    private List<Transaction> transactions;

    public Trip(String name, int budget, boolean isCurrent, boolean isBusiness, Calendar date) {
        this.name = name;
        this.budget = budget;
        this.isCurrent = isCurrent;
        this.isBusiness = isBusiness;
        this.date = date;
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

    @Column(name = "budget")
    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Column(name = "current")
    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    @Column(name = "business")
    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean business) {
        isBusiness = business;
    }

    @Column(name = "date")
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @OneToMany(mappedBy = "trip", fetch = FetchType.EAGER)
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
