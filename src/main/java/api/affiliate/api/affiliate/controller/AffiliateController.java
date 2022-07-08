package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.AffiliateBusiness;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.affiliate.AffiliateRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/affiliate")
public class AffiliateController {

    public final AffiliateBusiness affiliateBusiness;

    public AffiliateController(AffiliateBusiness affiliateBusiness) {
        this.affiliateBusiness = affiliateBusiness;
    }



    //    POST
    @PostMapping("/affiliate-register")
    public ResponseEntity<Object> register(@RequestParam(value = "file") MultipartFile file,
                                           @RequestParam(value = "profile")Object profile) throws BaseException {
        Object register = affiliateBusiness.register(file, profile);
        return ResponseEntity.ok(register);
    }






}
