package api.affiliate.api.affiliate.repository;

import api.affiliate.api.affiliate.entity.CartItemTable;
import api.affiliate.api.affiliate.entity.ProductTable;
import api.affiliate.api.affiliate.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItemTable, Integer> {


    List<CartItemTable> findAllByUserId(String userId);
    Optional <CartItemTable> findByUserId(String userId);

    Optional <CartItemTable> findByProductId(Integer productId);

    Optional<CartItemTable> findByCartId(Integer cartId);


    Optional<CartItemTable> findByProductIdAndProductAmount(Integer productId, Integer amount);

    CartItemTable findByUserIdAndProductId(UserTable user, ProductTable product);
}
