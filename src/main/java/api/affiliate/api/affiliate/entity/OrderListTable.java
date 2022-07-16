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
    @Column(name = "order_id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @Column(name = "date", nullable = false)
    private Date date = new Date();

    @Column(name = "image")
    private String image;

    @JsonIgnore
    @Column(name = "fk_user_id", nullable = false, unique = true)
    private String userId;

//nullable = false ห้ามว่าง
}
