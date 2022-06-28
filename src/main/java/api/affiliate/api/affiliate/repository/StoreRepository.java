package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.StoreTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreTable, Integer> {

    boolean existsByStore(String store);

    @Override
    List<StoreTable> findAll();

    @Override
    Optional<StoreTable> findById(Integer id);

    Optional<StoreTable> findByUserId(String id);


}
