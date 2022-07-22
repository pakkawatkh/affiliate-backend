package api.affiliate.api.affiliate.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDetailTable {

    @Id
    @Column(name = "orders_id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderDetailId;

    @Column(name = "fk_product_id", nullable = false)
    private String productId;

    @Column(name = "fk_product_price", nullable = false)
    private String productPrice;

    @Column(name = "fk_product_amount", nullable = false)
    private String productAmount;

    @Column(name = "fk_product_total", nullable = false, length = 10)
    private String  productTotal;

    @Column(name = "fk_order_id", nullable = false)
    private String orderId;

//nullable = false ห้ามว่าง
}
