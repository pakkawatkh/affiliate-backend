package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.CustomerTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerTable, String> {

    Optional<CustomerTable> findByCustomerName(String customerName);

    @Override
    List<CustomerTable> findAll();

    boolean existsByCustomerName(String customerName);

    @Override
    Optional<CustomerTable> findById(String id);
}
