package api.affiliate.api.affiliate.model.withdraw;

import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
import api.affiliate.api.affiliate.model.store.StoreRegisterRequest;
import api.affiliate.api.affiliate.model.store.StoreRequest;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WithdrawResponse {

    private Integer withdrawId;
    private Date date;
    private String image;
    private Integer totalPrice;
    private String status;
    private Integer storeId;
    private StoreTable store;
    private UserTable user;

}
