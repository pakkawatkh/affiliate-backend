package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.OrderListTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrderListRepository extends JpaRepository<OrderListTable, Integer> {


    @Override
    List<OrderListTable> findAll();

    List<OrderListTable> findAllOrderByUserId(String userId);

    Optional<OrderListTable> findByOrderListId(Integer orderId);

    @Query(value = """
            select * from order_list ol
                inner join order_detail od on ol.order_list_id = od.fk_order_list_id
                inner join product p on p.product_id = od.fk_product_id
                where p.fk_store_id =:store_id
                group by ol.order_list_id
             """, nativeQuery = true)
    List<OrderListTable> getOrderByStoreId(@Param("store_id") Integer storeId);

    @Query("""
            select o from OrderListTable as o
            where o.orderListId =:id
            and o.storeId =:store_id
            """)
    OrderListTable getOrderListDetailByIdAndStore(@Param("store_id") Integer store_id, @Param("id") Integer id);


    @Query("""
            select o from OrderListTable as o
            where o.orderListId =:id
            and o.userId =:user_id
            """)
    OrderListTable getOrderListDetailByIdAndUser(@Param("user_id") String user_id, @Param("id") Integer id);


    @Query(value = """
            select * from order_list ol
                inner join order_detail od on ol.order_list_id = od.fk_order_list_id
                inner join product p on p.product_id = od.fk_product_id
                where p.fk_store_id =:store_id 
                and (ol.status like :status)
                group by ol.order_list_id
             """, nativeQuery = true)
    List<OrderListTable> getOrderStatus(@Param("store_id") Integer storeId, @Param("status") String status);


    @Query(value = """
            select sum(ol.total_price) from order_list ol
                where ol.status like 'success'
                and ol.fk_store_id = :store_id
            """, nativeQuery = true)
    Object getTotalPriceByOrderStatusSuccess(@Param("store_id") Integer storeId);


    @Modifying
    @Transactional
    @Query(value = """
            UPDATE OrderListTable as o
                SET o.status = 'withdraw money'
                WHERE o.status like 'success'
                and o.storeId = :store_id
             """)
    void updateOrderStatusIsWithDrawMoney(@Param("store_id") Integer storeId);


//    @Query("""
//            select MAX(o.orderListId) from OrderListTable as o
//            where o.userId =: userId
//            """)
//    OrderListTable getOrderListMaxIdByUser(@Param("order_list_id") Integer orderListId,
//                                           @Param("fk_user_id") String userId);


//    @Query("""
//            select MAX(o.orderListId) from OrderListTable as o
//            """)
//    OrderListTable getOrderListMaxId(@Param("order_list_id") Integer orderListId);


}
