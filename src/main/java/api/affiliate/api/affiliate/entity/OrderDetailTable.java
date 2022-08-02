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

    @Column(name = "product_price", nullable = false)
    private Integer productPrice;

    @Column(name = "product_amount", nullable = false)
    private Integer productAmount;

    @Column(name = "product_total", nullable = false)
    private Integer  productTotal;

    @Column(name = "fk_store_id", nullable = false)
    private Integer  storeId;

    @Column(name = "fk_user_id", nullable = false)
    private String  userId;

    @Column(name = "full_name", nullable = false)
    private String  fullName;

    @Column(name = "tel", nullable = false)
    private Integer  tel;

    @Column(name = "address", nullable = false)
    private String  address;

    @Column(name = "sub", nullable = false)
    private String  sub;

    @Column(name = "district", nullable = false)
    private String  district;

    @Column(name = "province", nullable = false)
    private String  province;

    @Column(name = "postalCode", nullable = false)
    private Integer  postalCode;

    @Column(name = "fk_order_list_id")
    private String orderListId;

//nullable = false ห้ามว่าง
}
