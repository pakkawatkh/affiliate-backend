package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.ProductTable;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductTable, Integer> {


    @Override
    List<ProductTable> findAll();

    List<ProductTable> findAllProductByStoreId(Integer storeId);

    List<ProductTable> findAllByStatusIsTrue();

    Optional<ProductTable> findByProductId(Integer productId);

    Optional<ProductTable> findByProductIdAndStoreId(Integer productId, Integer storeId);

    boolean existsByProductName(String productName);



}
