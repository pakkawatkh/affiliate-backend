package api.affiliate.api.affiliate.exception;

public class WithdrawException extends BaseException {

    public WithdrawException(String code) {
        super(code);
    }

    public static WithdrawException withdrawNull() {
        return new WithdrawException("ไม่พบข้อมูล withdraw");
    }


    public static WithdrawException createFail() {
        return new WithdrawException("Failed to create withdraw");
    }

    public static WithdrawException addSlipFail() {
        return new WithdrawException("Failed to add slip withdraw");
    }

    public static WithdrawException withdrawFail() {
        return new WithdrawException("You don't have any withdrawal balance.");
    }


}