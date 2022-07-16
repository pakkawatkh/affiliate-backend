package api.affiliate.api.affiliate.exception;

public class OrderException extends BaseException {

    public OrderException(String code) {
        super(code);
    }


    public static OrderException orderRequestInvalid() {
        return new OrderException("กรุณาแนบหลักฐานการชำระเงิน");
    }

    public static OrderException createOrderDuplicated() {
        return new OrderException("orderName ซ้ำ");
    }


    public static OrderException orderNull() {
        return new OrderException("ไม่พบข้อมูล order");
    }

    public static OrderException roleUserNotAllowed() {
        return new OrderException("ไม่อนุญาต");
    }

}