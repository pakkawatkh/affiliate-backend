package api.affiliate.api.affiliate.repository;


import api.affiliate.api.affiliate.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserTable, String> {

    Optional<UserTable> findByUserName(String userName);

    @Override
    List<UserTable> findAll();

    boolean existsByUserName(String userName);

    @Override
    Optional<UserTable> findById(String id);
}
