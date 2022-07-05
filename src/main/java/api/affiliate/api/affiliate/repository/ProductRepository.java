package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.ProductTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductTable, Integer> {


    @Override
    List<ProductTable> findAll();

    List<ProductTable> findAllProductByStoreId(Integer storeId);

    List<ProductTable> findAllByStatusIsTrue();
/*
*   findBy
*       Status*
*             And
*                   StoreId*
*                           (boolean Status(true) ,Integer storeId(6) )
*
*
* */
//    List<ProductTable> findByStatusAndStoreId(boolean status,Integer storeId);

    List<ProductTable> findByStatusIsTrueAndStoreId(Integer storeId);

    Optional<ProductTable> findByProductId(Integer productId);

    Optional<ProductTable> findByProductIdAndStoreId(Integer productId, Integer storeId);

    boolean existsByProductName(String productName);


}
