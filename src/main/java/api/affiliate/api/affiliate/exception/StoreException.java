package api.affiliate.api.affiliate.exception;

public class StoreException extends BaseException {

    public StoreException(String code) {super(code);}

    //create
    public static StoreException storeRequestInvalid() {
        return new StoreException("กรุณากรอกข้อมูลให้ถูกต้อง");
    }

    public static StoreException createStoreNameDuplicated() {
        return new StoreException("storeName ซ้ำ");
    }


    //login
    public static StoreException storeNameNull() {
        return new StoreException("ไม่พบ storeName");
    }

    public static StoreException storeIdNull() {
        return new StoreException("ไม่พบ store id");
    }

    public static StoreException passWordInvalid() {
        return new StoreException("passWord ไม่ถูกต้อง");
    }

    public static StoreException loginFail() {
        return new StoreException("login ไม่สำเร็จ");
    }


    public static StoreException roleUserNotAllowed() {
        return new StoreException("ไม่อนุญาต");
    }
}
