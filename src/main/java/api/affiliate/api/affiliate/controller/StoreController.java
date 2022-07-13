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


    @GetMapping("/getMyProfile-store")
    public Object getMyProfileStore() throws BaseException{
        Object store = storeBisiness.getMyProfileStore();
        return ResponseEntity.ok(store);
    }



//    POST
    @PostMapping("/store-register")
    public ResponseEntity<Object> register(@RequestBody StoreRegisterRequest request) throws BaseException {
        System.out.println(request);
        Object register = storeBisiness.register(request);
        return ResponseEntity.ok(register);
    }


    @PutMapping("/store-update")
    public ResponseEntity<Object> updateStore(@RequestBody StoreRegisterRequest request) throws BaseException {
        Object update = storeBisiness.updateStore(request);
        return ResponseEntity.ok(update);

    }

    @PutMapping("/delete-myStore")
    public Object updateStatusStore() throws BaseException {
        Object store = storeBisiness.updateStatusStore();
        return ResponseEntity.ok(store);
    }



}
