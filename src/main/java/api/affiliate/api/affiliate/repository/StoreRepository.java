package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.model.store.StoreRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreTable, Integer> {

    boolean existsByStore(String store);

    @Override
    List<StoreTable> findAll();

    @Override
    Optional<StoreTable> findById(Integer id);

    Optional<StoreTable> findByUserId(String id);


    @Query(value = """
            select distinct wd.* from withdraw wd
                where wd.withdraw_id =:withdraw_id
                and wd.fk_store_id =:store_id
             """, nativeQuery = true)
    Optional<StoreTable> getWithdrawIdAndStoreId(@Param("withdraw_id") Integer withdrawId, @Param("store_id") Integer storeId);


    @Query(value = """
            select st.* from store st
                where st.store_id =:store_id
                and st.fk_user_id =:user_id
             """, nativeQuery = true)
    Optional<StoreTable> getStoreIdAndUserId(@Param("store_id") Integer storeId, @Param("user_id") String userId);


}
