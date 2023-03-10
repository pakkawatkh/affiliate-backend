package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.model.store.TotalPriceResponse;
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
            select distinct ol.* from order_list ol
                inner join order_detail od on ol.order_list_id = od.fk_order_list_id
                inner join product p on p.product_id = od.fk_product_id
                where p.fk_store_id =:store_id
              
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
            select * from order_list o
            where o.status like :status
             """, nativeQuery = true)
    List<OrderListTable> getOrderStatus(@Param("status") String status);


    @Query(value = """
            select distinct ol.*
                from order_list ol
                where ol.fk_user_id = :user_id
                and (ol.status like :status)
             """, nativeQuery = true)
    List<OrderListTable> getOrderStatusByUser(@Param("user_id") String userId, @Param("status") String status);


    @Query(value = """
            select distinct ol.* from order_list ol
               inner join order_detail od on ol.order_list_id = od.fk_order_list_id
               inner join product p on p.product_id = od.fk_product_id
               where p.fk_store_id =:store_id 
               and (ol.status like :status)
            """, nativeQuery = true)
    List<OrderListTable> getOrderStatus(@Param("store_id") Integer storeId, @Param("status") String status);

    @Query(value = """
            select distinct ol.* from order_list ol
                where ol.fk_user_id =:user_id 
                and (ol.status = 'success')
                and (ol.dlv_status = false)
             """, nativeQuery = true)
    List<OrderListTable> getOrderDeliverIsFalse(@Param("user_id") String userId);

    @Query(value = """
            select distinct ol.* from order_list ol
                where ol.fk_store_id =:store_id 
                and (ol.status = 'success')
                and (ol.dlv_status = false)
             """, nativeQuery = true)
    List<OrderListTable> getOrderDeliverIsFalseByStore(@Param("store_id") Integer storeId);


    @Query(value = """
            select sum(ol.total_price) from order_list ol
                where ol.status like 'success'
                and ol.dlv_status = true
                and ol.fk_store_id = :store_id
            """, nativeQuery = true)
    Integer getTotalPriceByOrderStatusSuccess(@Param("store_id") Integer storeId);

    @Query(value = """
            select new api.affiliate.api.affiliate.model.store.TotalPriceResponse( sum(ol.totalPrice)) from OrderListTable as ol
                where ol.status like 'success'
                and ol.dlvStatus = true
                and ol.storeId = :store_id
            """)
    TotalPriceResponse getTotalPriceByCommission();

    @Query(value = """
            select ol.* from order_list ol
                where ol.status like 'success'
                and ol.dlv_status = true
                and ol.fk_store_id = :store_id
            """, nativeQuery = true)
    List<OrderListTable> getOrderSuccessByStoreId(@Param("store_id") Integer storeId);


    @Query(value = """
            select count(*) from order_list ol
                where ol.status like 'success'
                and ol.fk_store_id = :store_id
            """, nativeQuery = true)
    long countByOrderStatusSuccess(@Param("store_id") Integer storeId);


    boolean existsByStatusAndStoreIdAndDlvStatusIsTrue(String status, Integer storeId);


    @Modifying
    @Transactional
    @Query(value = """
            UPDATE OrderListTable as ol
                SET ol.status = 'withdraw money' , ol.withdrawId =:withdraw_id
                WHERE ol.status = 'success' and ol.storeId =:store_id
            """)
    void updateOrderStatusIsWithDrawMoney(@Param("withdraw_id") Integer withdrawId, @Param("store_id") Integer storeId);


    @Query(value = """
            select * from order_list o
                where o.dlv_status = true
                and (o.status = 'success' or o.status = 'withdraw money')
                and o.fk_user_id =:user_id
             """, nativeQuery = true)
    List<OrderListTable> getOrderDeliverStatusIsTrue(@Param("user_id") String userId);


    @Query(value = """
            select * from order_list o
                where o.dlv_status = true
                and (o.status = 'success' or o.status = 'withdraw money')
                and o.fk_store_id =:store_id
             """, nativeQuery = true)
    List<OrderListTable> getOrderDeliverStatusIsTrueByStore(@Param("store_id") Integer storeId);

}
