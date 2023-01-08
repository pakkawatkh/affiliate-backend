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



    //    GET
    @GetMapping("/getMyProfile-affiliate")
    public ResponseEntity<Object> getMyProfile(){
        Object profile = affiliateBusiness.getMyAffiliate();
        return ResponseEntity.ok(profile);
    }


    //    POST
    @PostMapping("/affiliate-register")
    public ResponseEntity<Object> register(@RequestBody AffiliateRegisterRequest request) {
        Object register = affiliateBusiness.register(request);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/affiliate-share-product/{id}")
    public ResponseEntity<Object> shareProduct(@PathVariable Integer id) {
        Object affiliate = affiliateBusiness.shareProduct(id);
        return ResponseEntity.ok(affiliate);
    }




    //    PUT
    @PutMapping("/affiliate-update")
    public ResponseEntity<Object> updateProfile(@RequestBody AffiliateRegisterRequest request) {
        Object update = affiliateBusiness.updateProfile(request);
        return ResponseEntity.ok(update);
    }


    @PutMapping("/delete-myAffiliate")
    public ResponseEntity<Object> updateMyStatusAffiliate() {
        Object affiliate = affiliateBusiness.updateMyStatusAffiliate();
        return ResponseEntity.ok(affiliate);
    }







}
