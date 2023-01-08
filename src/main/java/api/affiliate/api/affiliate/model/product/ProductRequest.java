package api.affiliate.api.affiliate.model.product;

import lombok.Data;

@Data
public class ProductRequest {
    private Integer productId;
    private Integer amount;
    private String linkId;
}
