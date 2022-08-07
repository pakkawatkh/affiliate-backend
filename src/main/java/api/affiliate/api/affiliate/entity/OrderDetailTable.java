package api.affiliate.api.affiliate.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDetailTable {

    @Id
    @Column(name = "order_detail_id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderDetailId;

    @Column(name = "fk_product_id", nullable = false)
    private Integer productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private Integer productPrice;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "product_total", nullable = false)
    private Integer  productTotal;

    @Column(name = "fk_order_list_id", nullable = false)
    private Integer orderListId;

//nullable = false ห้ามว่าง
}
