package api.affiliate.api.affiliate.exception;

public class AffiliateException extends BaseException {

    public AffiliateException(String code) {
        super(code);
    }


    //create
    public static AffiliateException affiliateRequestInvalid() {
        return new AffiliateException("กรุณากรอกข้อมูลให้ถูกต้อง");
    }

    public static AffiliateException createAffiliateDuplicated() {
        return new AffiliateException("affiliateName ซ้ำ");
    }


    //login
    public static AffiliateException affiliateNameNull() {
        return new AffiliateException("ไม่พบ affiliateName");
    }

}