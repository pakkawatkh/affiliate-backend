package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.OrderDetailTable;

import api.affiliate.api.affiliate.entity.OrderListTable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetailTable, Integer> {


    @Override
    List<OrderDetailTable> findAll();

//    List<OrderDetailTable> findAllByStoreId(Integer storeId);

    void deleteByOrderListId(Integer orderListId);

    List<OrderDetailTable> findAllByOrderListId(Integer orderListId);

    //
//    @Query(value = """
//            select max(order_detail_id) from order_detail od
//             """, nativeQuery = true)
//    Optional<OrderDetailTable> getOrderIdMax();
}
