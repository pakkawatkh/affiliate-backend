package api.affiliate.api.affiliate.repository;


import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserTable, String> {

    Optional<UserTable> findByUserName(String userName);

    @Override
    List<UserTable> findAll();

    boolean existsByUserName(String userName);

    @Override
    Optional<UserTable> findById(String id);

    @Query(value = """
            select * from product p 
            where p.product_name like :product_name
             """, nativeQuery = true)
    List<ProductTable> getProductSearch(@Param("product_name") String productName);


    @Query(value = """
            select * from users as u
            where u.role !=:roles
            """, nativeQuery = true)
    List<UserTable> getAllUserByAdmin(@Param("roles") String roles);

    @Query(value = """
            select * from users as u
            where u.role =:role
            """, nativeQuery = true)
    List<UserTable> getRoleUser(@Param("role") String role);

    @Query(value = """
            select * from users as u
            where u.role =:roles
            """, nativeQuery = true)
    List<UserTable> getAllRole(@Param("roles") String roles);

    @Query(value = """
            select * from users as u
            where u.role =:roles or u.role =:role
            """, nativeQuery = true)
    List<UserTable> getAllRole(@Param("roles") String roles, @Param("role") String role);






}
