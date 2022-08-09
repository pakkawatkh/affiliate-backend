package api.affiliate.api.affiliate.exception;

public class OrderException extends BaseException {

    public OrderException(String code) {
        super(code);
    }


    public static OrderException orderRequestInvalid() {
        return new OrderException("Failed to create order");
    }

    public static OrderException orderNull() {
        return new OrderException("ไม่พบข้อมูล order");
    }

    public static OrderException roleUserNotAllowed() {
        return new OrderException("ไม่อนุญาต");
    }

}