package api.affiliate.api.affiliate.exception;

public class LinkException extends BaseException {

    public LinkException(String code) {
        super(code);
    }


    public static LinkException linkIdIsNull() {
        return new LinkException("ไม่พบข้อมูล Link ID");
    }



}