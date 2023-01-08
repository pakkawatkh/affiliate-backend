package api.affiliate.api.affiliate.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "link")
public class LinkTable {


    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(nullable = false, updatable = false, length = 36, unique = true)
    private String linkId;


//    จำนวนลูกค้าซื้อสินค้าจากลิ้งค์สำเร็จ
    @Column(name = "amount", nullable = false)
    private Integer amount = 0;
    @Column(name = "amount_withdraw", nullable = false)
    private Integer amountWithdraw = 0;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "create_date", nullable = false)
    private Date creatDate = new Date();

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @Column(name = "fk_product_id", nullable = false)
    private Integer productId;

    @Column(name = "fk_affiliate_id", nullable = false)
    private Integer affiliateId;

    @Column(name = "fk_store_id", nullable = false)
    private Integer storeId;
 }
