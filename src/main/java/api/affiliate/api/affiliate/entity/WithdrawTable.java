package api.affiliate.api.affiliate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "withdraw")
public class WithdrawTable {

    @Id
    @Column(name = "withdraw_id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer withdrawId;

    @Column(name = "date", nullable = false)
    private Date date = new Date();

    @Column()
    private Date dateStWithdrawSuccess;

    @Column(name = "image")
    private String image = null;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "status", nullable = false)
    private String status;


    @Column(name = "fk_store_id", nullable = false)
    private Integer storeId;



}
