package api.affiliate.api.affiliate.exception;

public class ProductException extends BaseException {

    public ProductException(String code) {super(code);}

    // create
    public static ProductException productRequestInvalid() {
        return new ProductException("กรุณากรอกข้อมูลให้ถูกต้อง");
    }

    public static ProductException createProductNameDuplicated() {
        return new ProductException("product Name ซ้ำ");
    }

    public static ProductException createProductFail() {
        return new ProductException("create product fail");
    }

    public static ProductException roleUserNotAllowed() {
        return new ProductException("ไม่อนุญาต");
    }




    // update
    public static ProductException updateProductFail() {
        return new ProductException("update product fail");
    }


    // login
    public static ProductException productNull() {
        return new ProductException("ไม่พบ product Name");
    }

    public static ProductException passWordInvalid() {
        return new ProductException("passWord ไม่ถูกต้อง");
    }

    public static ProductException loginFail() {
        return new ProductException("login ไม่สำเร็จ");
    }
}
