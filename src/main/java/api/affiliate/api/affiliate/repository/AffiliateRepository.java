package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.AffiliateTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AffiliateRepository extends JpaRepository<AffiliateTable, Integer> {


    Optional<AffiliateTable> findByUserId(String userId);
}
