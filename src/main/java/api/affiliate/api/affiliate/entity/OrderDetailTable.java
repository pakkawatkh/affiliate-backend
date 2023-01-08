package api.affiliate.api.affiliate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDetailTable {

    @Id
    @Column(name = "order_detail_id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JsonIgnore
    @Column(name = "fk_order_list_id", nullable = false)
    private Integer orderListId;

    @Column(name = "fk_link_id", nullable = true)
    private String linkId;

//nullable = false ห้ามว่าง
}
