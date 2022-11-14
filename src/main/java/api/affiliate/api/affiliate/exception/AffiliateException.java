package api.affiliate.api.affiliate.exception;

public class AffiliateException extends BaseException {

    public AffiliateException(String code) {
        super(code);
    }


    public static AffiliateException affiliateRequestInvalid() {
        return new AffiliateException("กรุณากรอกข้อมูลให้ถูกต้อง");
    }

    public static AffiliateException createAffiliateDuplicated() {
        return new AffiliateException("affiliateName ซ้ำ");
    }


    public static AffiliateException affiliateNameNull() {
        return new AffiliateException("ไม่พบข้อมูล affiliate");
    }

    public static AffiliateException roleUserNotAllowed() {
        return new AffiliateException("ไม่อนุญาต");
    }

}