package api.affiliate.api.affiliate.model.order;

import api.affiliate.api.affiliate.model.product.ProductRequest;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private  String fullName;

    private String tel;

    private String address;

    private String sub;

    private String district;

    private String province;

    private Integer postalCode;

    private List<ProductRequest> products;
}
