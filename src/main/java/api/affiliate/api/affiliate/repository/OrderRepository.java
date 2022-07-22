package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.entity.ProductTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderListTable, Integer> {


    @Override
    List<OrderListTable> findAll();

    List<OrderListTable>  findAllOrderByUserId(String userId);
    Optional<OrderListTable> findByUserId(String userId);


//
//
//    @Query(value = """
//     select * from order_list ol
//     inner join
//     """, nativeQuery = true)
//    List<OrderListTable> get();
}
