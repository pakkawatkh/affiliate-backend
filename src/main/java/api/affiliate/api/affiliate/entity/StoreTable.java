package api.affiliate.api.affiliate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "store")
public class StoreTable {

    @Id
    @Column(name = "store_id" )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer storeId;

    //      ชื่อร้าน
    @Column(name = "store", nullable = false, length = 60, unique = true)
    private String store;

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
