package api.affiliate.api.affiliate.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity()
@Table(name = "system_data")
public class SystemTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private Date date = new Date();

    private Float percent;

    private Boolean status = true;

}
