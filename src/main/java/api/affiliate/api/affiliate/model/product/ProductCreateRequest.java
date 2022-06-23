package api.affiliate.api.affiliate.model.product;

import lombok.Data;


@Data
public class ProductCreateRequest {

    private String productName;

    private String  productPrice;

    private String productDetail;

//    public void valid() throws BaseException {
//        if (customerName == null || passWord == null) {
//            throw CustomerException.customerRequestInvalid();
//        }
//        if (customerName.isBlank() || passWord.isBlank()) {
//            throw CustomerException.customerRequestInvalid();
//        }
//    }
}
