package api.affiliate.api.affiliate.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "product")
public class ProductTable {

    @Id
    @Column(name = "product_id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;

    @Column(name = "product_name", nullable = false, unique = true, length = 120)
    private String productName;

    @Column(name = "product_price", nullable = false, length = 10)
    private String  productPrice;

    @Column(name = "product_detail", nullable = false, length = 120)
    private String productDetail;

    @Column(name = "fk_store_id", nullable = false)
    private Integer storeId;


}
