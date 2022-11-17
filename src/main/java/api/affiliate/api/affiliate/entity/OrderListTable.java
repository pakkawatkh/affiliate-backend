package api.affiliate.api.affiliate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "order_list")
public class OrderListTable {

    @Id
    @Column(name = "order_list_id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderListId;

    @Column(name = "date", nullable = false)
    private Date date = new Date();

    @Column(name = "image")
    private String image;

    @Column(name = "status", nullable = false)
    private String status = "true";

    @Column(name = "fk_user_id", nullable = false)
    private String userId;

    @Column(name = "full_name", nullable = false)
    private String  fullName;

    @Column(name = "tel", nullable = false)
    private String  tel;

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

    @Column(name = "totalPrice")
    private Integer  totalPrice;

    @Column(name = "fk_store_id", nullable = false)
    private Integer  storeId;

    @Column(name = "fk_withdraw_id", nullable = true)
    private Integer withdrawId = null;


//
//    @Column(name = "fk_cart_id", nullable = false)
//    private Integer cartId;

//nullable = false ห้ามว่าง
}
