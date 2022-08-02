package api.affiliate.api.affiliate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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
    private Integer  productPrice;

    @Column(name = "product_detail", nullable = false, length = 5000)
    private String productDetail;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @Column(name = "created", nullable = false)
    private Date created = new Date();

    @Column(name = "updated", nullable = false)
    private Date updated = new Date();

    @Column(name = "image")
    private String image;

    @Column(name = "fk_store_id", nullable = false)
    private Integer storeId;

//nullable = false ห้ามว่าง
}
