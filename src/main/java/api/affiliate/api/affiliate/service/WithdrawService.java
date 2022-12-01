package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.OrderListTable;
import api.affiliate.api.affiliate.entity.WithdrawTable;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.exception.WithdrawException;
import api.affiliate.api.affiliate.repository.WithdrawRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class WithdrawService {

    public final WithdrawRepository withdrawRepository;


    public WithdrawTable findById(Integer withdrawId){
        Optional<WithdrawTable> withdraw = withdrawRepository.findById(withdrawId);
        return withdraw.get();
    }

    public List<WithdrawTable> getWithdrawStatus(String status) {
        List<WithdrawTable> order = withdrawRepository.getWithdrawStatus(status);
        return order;
    }

    @SneakyThrows
//    public WithdrawTable createWithdrawByOrder(Integer totalPrice) {
//        WithdrawTable withdraw = new WithdrawTable();
//        withdraw.setDate(new Date());
//        withdraw.setTotalPrice(totalPrice);
//        withdraw.setStatus("withdraw money");
//        try {
//            return withdrawRepository.save(withdraw);
//        } catch (Exception e) {
//            throw WithdrawException.createFail();
//        }
    public WithdrawTable createWithdrawByOrder(Integer totalPrice, Integer storeId) {
        WithdrawTable withdraw = new WithdrawTable();
        withdraw.setDate(new Date());
        withdraw.setTotalPrice(totalPrice);
        withdraw.setStatus("withdraw money");
        withdraw.setStoreId(storeId);

        try {
            return withdrawRepository.save(withdraw);
        } catch (Exception e) {
            throw WithdrawException.createFail();
        }

    }


    @SneakyThrows
    public void  updateOrderStatusIsWithDrawSuccess(WithdrawTable withdraw, String img) {
        withdraw.setImage(img);
        withdraw.setDate(new Date());
        withdraw.setStatus("withdraw success");
        try {
            withdrawRepository.save(withdraw);
        } catch (Exception e) {
            throw WithdrawException.addSlipFail();
        }
    }


}
