package api.affiliate.api.affiliate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;

@Entity
@Data
@Table(name = "customer")
public class CustomerTable {

    @Id
    @Column(name = "customer_id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;

    //      ชื่อบัญชี
    @Column(name = "bank_nameAccount", nullable = false, length = 120)
    private String bankNameAccount;

    //      ชื่อธนาคาร
    @Column(name = "bank_name", nullable = false, length = 120)
    private String bankName;

    //      เลขบัญชี
    @Column(name = "bank_number", nullable = false, length = 20)
    private String bankNumber;

@JsonIgnore
    @Column(name = "fk_user_id", nullable = false, unique = true)
    private String userId;
}
