package cs603.hw4;

import javax.persistence.*;
import java.util.ArrayList;


public class Purchase {
    EntityManager emgr;
    ArrayList<LineItem> items = new ArrayList<>();
    int total = 0;

    public void setEntityManager(EntityManager _emgr) {
        emgr = _emgr;
        emgr.getTransaction().begin();
    }
    private Customer customer;
    public void setCustomer(Customer cust) {
        customer = cust;
    }

    public void add(Product prod, int ct) {
        Query prodQ = emgr.createNamedQuery("Product.findByProductId");
        try {
            // Product check = (Product) prodQ.setParameter("productId", prod.getProductId()).getSingleResult();
            Query q = prodQ.setParameter("productId", prod.getProductId());
            Product check = (Product) q.getSingleResult();
            LineItem item = new LineItem();
            item.setProdId(prod);
            item.setCount(ct);
            emgr.persist(item);
            total += prod.getPrice() * ct;
            items.add(item);
        }
        catch (NoResultException exc) {
            System.err.println("Product not found");
        }

    }

    public void complete() {
        int limit = customer.getLimit();
        if (total <= limit) {
            customer.setLimit(limit - total);
            emgr.getTransaction().commit();
        }
        else emgr.getTransaction().rollback();
    }
}
