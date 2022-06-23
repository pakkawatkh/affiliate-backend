package api.affiliate.api.affiliate.exception;

public class UserException extends BaseException {

    public UserException(String code) {
        super(code);
    }


    //create
    public static UserException userRequestInvalid() {
        return new UserException("กรุณากรอกข้อมูลให้ถูกต้อง");
    }

    public static UserException createUserNameDuplicated() {
        return new UserException("userName ซ้ำ");
    }


    //login
    public static UserException userNameNull() {
        return new UserException("ไม่พบ userName");
    }

    public static UserException passWordInvalid() {
        return new UserException("passWord ไม่ถูกต้อง");
    }

    public static UserException loginFail() {
        return new UserException("login ไม่สำเร็จ");
    }

    //  create
//    public static UserException createUserNameNull() {
//        return new UserException("create.userName.null");
//    }
//
//
//    public static UserException createUserNameDuplicated() {
//        return new UserException("create.userName.duplicate");
//    }
//
//
//    public static UserException createPassWordNull(String userName) {
//        return new UserException("create.passWord.null");
//    }


}