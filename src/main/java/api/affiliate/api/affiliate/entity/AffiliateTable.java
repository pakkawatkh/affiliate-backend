package api.affiliate.api.affiliate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;

@Entity
@Data
@Table(name = "affiliate")
public class AffiliateTable {

    @Id
    @Column(name = "affiliate_id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer affiliateId;

    //      ชื่อบัญชี
    @Column(name = "bank_nameAccount", nullable = false, length = 120)
    private String bankNameAccount;

    //      ชื่อธนาคาร
    @Column(name = "bank_name", nullable = false, length = 120)
    private String bankName;

    //      เลขบัญชี
    @Column(name = "bank_number", nullable = false, length = 20)
    private String bankNumber;


    @Column(name = "status", nullable = false)
    private Boolean status = true;


    @JsonIgnore
    @Column(name = "fk_user_id", nullable = false, unique = true)
    private String userId;
}
