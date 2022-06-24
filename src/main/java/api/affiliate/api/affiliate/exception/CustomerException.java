package api.affiliate.api.affiliate.exception;

public class CustomerException extends BaseException {

    public CustomerException(String code) {
        super(code);
    }


    //create
    public static CustomerException customerRequestInvalid() {
        return new CustomerException("กรุณากรอกข้อมูลให้ถูกต้อง");
    }

    public static CustomerException createCustomerDuplicated() {
        return new CustomerException("customerName ซ้ำ");
    }


    //login
    public static CustomerException customerNameNull() {
        return new CustomerException("ไม่พบ customerName");
    }

    public static CustomerException passWordInvalid() {
        return new CustomerException("passWord ไม่ถูกต้อง");
    }

    public static CustomerException loginFail() {
        return new CustomerException("login ไม่สำเร็จ");
    }
}
