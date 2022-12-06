package api.affiliate.api.affiliate.model.order;

import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.exception.ProductException;
import api.affiliate.api.affiliate.model.product.ProductRequest;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.List;

@Data
public class OrderTrackingRequest {

    private String trackingNumber;

    @SneakyThrows
    public void valid(){
        if (trackingNumber == null) {
            throw OrderException.trackingNull();
        }
        if (trackingNumber.isBlank()) {
            throw OrderException.trackingNull();
        }
    }
}
