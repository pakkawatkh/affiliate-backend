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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderListId;

    @Column(name = "price", nullable = false, length = 10)
    private String  price;

    @Column(name = "day", nullable = false, length = 10)
    private String  day;

    @Column(name = "time", nullable = false, length = 10)
    private String  time;

    @Column(name = "date", nullable = false)
    private Date date = new Date();

    @Column(name = "image")
    private String image;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @Column(name = "fk_user_id", nullable = false)
    private String userId;

//nullable = false ห้ามว่าง
}
