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

    @Column(name = "image")
    private String image;

    @Column(name = "total_price", nullable = true)
    private Integer totalPrice;


}
