package api.affiliate.api.affiliate.repository;


import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.entity.WithdrawTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WithdrawRepository extends JpaRepository<WithdrawTable, Integer> {

    @Override
    List<WithdrawTable> findAll();

    @Override
    Optional<WithdrawTable> findById(Integer id);

    @Query(value = """
            select * from withdraw w 
            where w.status like :status
             """, nativeQuery = true)
    List<WithdrawTable> getWithdrawStatus(@Param("status") String status);


}
