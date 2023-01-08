package api.affiliate.api.affiliate.model.product;

import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.ProductException;
import lombok.Data;


@Data
public class ProductCreateRequest {

    private String productName;
    private Integer  productPrice;

    private String productDetail;

    private Float priceForShare;

    public void valid() throws BaseException {
        if (productName == null || productPrice == null) {
            throw ProductException.productRequestInvalid();
        }
        if (productName.isBlank() || productPrice.equals(null)) {
            throw ProductException.productRequestInvalid();
        }
    }
}
