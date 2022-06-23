package api.affiliate.api.affiliate.exception;

public class CustomerException extends BaseException {

    public CustomerException(String code) {
        super(code);
    }


    public static CustomerException customerNameNull() {
        return new CustomerException("ไม่พบ CustomerName");
    }
}
