package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.WithdrawBusiness;
import api.affiliate.api.affiliate.exception.BaseException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/withdraw")
public class WithdrawController {

    private final WithdrawBusiness withdrawBusiness;


//    @GetMapping("/get-withdraw-by-id/{id}")
//    public ResponseEntity<Object> getWithdrawById(@PathVariable Integer id) {
//        Object withdraw = withdrawBusiness.findByWithdrawById(id);
//        return ResponseEntity.ok(withdraw);
//    }




}
