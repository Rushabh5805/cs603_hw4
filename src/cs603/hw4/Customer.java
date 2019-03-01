package cs603.hw4;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    private Integer customerId;
    private String name;
    private Integer limit;

    public void setCustomerId(Integer id) {
        customerId = id;
    }

    public Integer getCustomerId() { return customerId; }

    public void setName(String _name) {
        name = _name;
    }

    public String getName() { return name; }

    public void setLimit(Integer _limit) { limit = _limit;  }
    public Integer getLimit() { return limit; }
}
