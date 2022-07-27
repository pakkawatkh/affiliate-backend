package api.affiliate.api.affiliate.exception;

public class CartException extends BaseException {

    public CartException(String code) {
        super(code);
    }


    public static CartException cartRequestInvalid() {
        return new CartException("กรุณากรอกข้อมูลให้ถูกต้อง");
    }



    public static CartException roleUserNotAllowed() {
        return new CartException("ไม่อนุญาต");
    }

}