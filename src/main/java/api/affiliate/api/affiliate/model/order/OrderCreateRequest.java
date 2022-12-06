package api.affiliate.api.affiliate.model.order;

import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.ProductException;
import lombok.Data;


@Data
public class OrderCreateRequest {

    private String productName;
    private String  productPrice;

    private String productDetail;

    public void valid() throws BaseException {
        if (productName == null || productPrice == null) {
            throw ProductException.productRequestInvalid();
        }
        if (productName.isBlank() || productPrice.isBlank()) {
            throw ProductException.productRequestInvalid();
        }
    }
}
