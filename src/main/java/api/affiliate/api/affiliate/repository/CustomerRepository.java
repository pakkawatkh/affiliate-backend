package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.CustomerTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<CustomerTable, Integer> {


    Optional<CustomerTable> findByUserId(String user);
}
