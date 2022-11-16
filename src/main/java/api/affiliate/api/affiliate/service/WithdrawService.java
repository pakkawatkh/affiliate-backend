package api.affiliate.api.affiliate.service;

import api.affiliate.api.affiliate.entity.WithdrawTable;
import api.affiliate.api.affiliate.exception.OrderException;
import api.affiliate.api.affiliate.exception.WithdrawException;
import api.affiliate.api.affiliate.repository.WithdrawRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class WithdrawService {

    public final WithdrawRepository withdrawRepository;


    @SneakyThrows
    public WithdrawTable createWithdrawByOrder(Integer totalPrice) {
        WithdrawTable withdraw = new WithdrawTable();
        withdraw.setImage("");
        withdraw.setTotalPrice(totalPrice);
        try {
            return withdrawRepository.save(withdraw);
        } catch (Exception e) {
            throw WithdrawException.createFail();
        }

    }


}
