package cs603.hw4;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


import static org.mockito.Mockito.*;

/**
 * Test class where all the tests are executed
 */
public class ProductTest {
    /**
     * Mocks to the EntityManager, EntityTransaction
     * and Query are created
     */
    @Mock private EntityManager entityManager;
    @Mock private EntityTransaction transaction;
    @Mock private Query query;
    Purchase purchase;
    Purchase purchase2;
    private Customer customer;
    private Product product;
    private Product product2;
    private Product product3;
    private LineItem item = new LineItem();

    /**
     * setUp() method which will run before all the test being executed
     * here we will create objects and set the values of product and customer.
     * Also we will mock the other methods which we will need tofake in order to
     * test the testcases.
     */
    @Before
    public void setUp(){
        purchase = new Purchase();
        purchase2 = new Purchase();
        entityManager = mock(EntityManager.class);
        transaction = mock(EntityTransaction.class);
        query = mock(Query.class);

        product = new Product();
        product.setProductId(12);
        product.setPrice(10);
        product2 = new Product();
        product2.setProductId(13);
        product2.setPrice(150);
        product3 = new Product();
        //product3.setProductId( (int)'k');
        product3.setPrice(9);

        customer = new Customer();
        customer.setCustomerId(1);
        customer.setName("abc");
        customer.setLimit(250);

        when(entityManager.getTransaction()).thenReturn(transaction);
        when(entityManager.createNamedQuery(anyString())).thenReturn(query);
        when(query.setParameter(anyString(), anyInt())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(product);

        purchase.setEntityManager(entityManager);
        purchase2.setEntityManager(entityManager);
        //purchase.add(product, 2);
        purchase.setCustomer(customer);

    }

    /**
     * test to check if the JPA transaction is started and
     * then it is commited
     * or not
     */
    @Test
    public void checkPurchaseSuccessful(){
        purchase.add(product,2);
        purchase.complete();
        verify(transaction).commit();
    }
    /**
     * Test to check if the JPA transaction is not started
     * and then it is rollbacked
     */
    @Test
    public void checkPurchaseUnsuccessful(){
        purchase.add(product2, 2);
        purchase.complete();
        verify(entityManager.getTransaction()).rollback();
    }
    /**
     * test to check if the valid ID is passed
     * then the lineitem is created
     */
    @Test
    public void checkValidID(){
        //verify(entityManager.persist(item);
        purchase2.add(product, 2);
        verify(purchase2.emgr).persist(any(LineItem.class));
    }
    /**
     * test to check if the valid ID is not passed
     * then the lineitem is not created
     */
    @Test
    public void checkInvalidID(){
       // doThrow(NoResultException.class).when(query).getSingleResult();
        purchase2.add(product3, 2);
        //verify(purchase2.emgr).persist(any(LineItem.class));
        verifyZeroInteractions(entityManager);
        //.persist(any(LineItem.class));
    }
}