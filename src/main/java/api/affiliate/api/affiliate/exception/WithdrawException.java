package api.affiliate.api.affiliate.exception;

public class WithdrawException extends BaseException {

    public WithdrawException(String code) {
        super(code);
    }


    public static WithdrawException createFail() {
        return new WithdrawException("Failed to create withdraw");
    }





}