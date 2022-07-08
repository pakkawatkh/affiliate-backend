package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.StoreBisiness;
import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.store.StoreRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    public final StoreBisiness storeBisiness;

    public StoreController(StoreBisiness storeBisiness) {
        this.storeBisiness = storeBisiness;
    }

    //    GET
    @GetMapping("/getAll-store")
    public List<StoreTable> getAllStore() {
        List<StoreTable> store = storeBisiness.findAllStore();
        return store;
    }


//    POST
    @PostMapping("/store-register")
    public ResponseEntity<Object> register(@RequestParam(value = "file") MultipartFile file,
                                           @RequestParam(value = "profile")Object profile) throws BaseException {
        System.out.println(profile);
        Object register = storeBisiness.register(file, profile);
        return ResponseEntity.ok(register);
    }


    @PutMapping("/store-update")
    public ResponseEntity<Object> updateStore(@RequestParam(value = "file")MultipartFile file,
                                              @RequestParam(value = "profile")Object profile) throws BaseException {
        Object update = storeBisiness.updateStore(file, profile);
        return ResponseEntity.ok(update);

    }




}
