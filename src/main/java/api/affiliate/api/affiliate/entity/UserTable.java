package api.affiliate.api.affiliate.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class UserTable {


    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(nullable = false, updatable = false, length = 36, unique = true)
    private String userId;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column(name = "user_name", nullable = false, unique = true, length = 120)
    private String userName;

    @JsonIgnore
    @Column(name = "password", nullable = false, length = 120)
    private String passWord;

    @Column(name = "full_name", nullable = false, length = 120)
    private String fullName;

    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @Column(name = "tel", nullable = false, length = 10)
    private String tel;

    @Column(name = "address", nullable = false, length = 120)
    private String address;

    @Column(name = "sub", nullable = false, length = 120)
    private String sub;

    @Column(name = "district", nullable = false, length = 120)
    private String district;

    @Column(name = "province", nullable = false, length = 120)
    private String province;

    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;


    public enum Role {USER, CUSTOMER, STORE, ADMIN}




 }
