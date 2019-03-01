package cs603.hw4;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
public class LineItem {
    @Id
    private Integer id;
    private Product prodId;
    private Integer count;

    public void setId(Integer _id) { id = _id; }
    public Integer getId() { return id; }
    public void setProdId(Product prod) { prodId = prod; }
    public Product getProdId() { return prodId; }

    public void setCount(Integer _count) { count = _count; }
    public Integer getCount() { return count; }
}
