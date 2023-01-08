package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.SystemTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SystemRepository extends JpaRepository<SystemTable, Long> {

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE SystemTable st
            SET st.status = false 
            """)
    void updateStatus();
}
