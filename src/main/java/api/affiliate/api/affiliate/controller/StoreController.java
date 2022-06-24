package api.affiliate.api.affiliate.controller;

import api.affiliate.api.affiliate.business.StoreBisiness;
import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.exception.BaseException;
import api.affiliate.api.affiliate.model.store.StoreRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> register(@RequestBody StoreRegisterRequest request) throws BaseException {
        System.out.println(request);
        Object register = storeBisiness.register(request);
        return ResponseEntity.ok(register);
    }




}
