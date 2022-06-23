package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.ProductTable;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductTable, Integer> {

    @Override
    List<ProductTable> findAll();

    Optional<ProductTable> findByProductName(String productName);

    Optional<ProductTable> findById(Integer id);


}
