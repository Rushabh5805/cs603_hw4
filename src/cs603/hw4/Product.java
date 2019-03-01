package cs603.hw4;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId")})
public class Product {
    @Id
    private Integer productId;
    private Integer price;
    public void setProductId(Integer _productId) { productId = _productId; }
    public Integer getProductId() { return productId; }
    public void setPrice(Integer _price) { price = _price; }
    public Integer getPrice() { return price; }
}
