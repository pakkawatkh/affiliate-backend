package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.OrderDetailTable;
import api.affiliate.api.affiliate.entity.OrderListTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetailTable, Integer> {


    @Override
    List<OrderDetailTable> findAll();



//
//    @Query(value = """
//     select * from order_detail
//     inner join product_id
//     """, nativeQuery = true)
//    List<OrderDetailTable> get();
}
