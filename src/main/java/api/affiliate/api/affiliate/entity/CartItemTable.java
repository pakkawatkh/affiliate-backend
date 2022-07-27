package api.affiliate.api.affiliate.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "cart")
public class CartItemTable {

    @Id
    @Column(name = "cart_id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;

    @Column(name = "product_amount", nullable = false, length = 10)
    private Integer  productAmount = 0;

    @Column(name = "product_total", nullable = false, length = 10)
    private Integer  productTotal = 0;

    @Column(name = "cart_status", nullable = false)
    private Boolean status = true;

    @Column(name = "created", nullable = false)
    private Date created = new Date();

    @Column(name = "fk_product_id", nullable = false)
    private Integer productId;

    @Column(name = "fk_user_id", nullable = false)
    private String userId;

//nullable = false ห้ามว่าง
}
