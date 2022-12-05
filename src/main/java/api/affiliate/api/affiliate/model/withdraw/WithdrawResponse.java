package api.affiliate.api.affiliate.model.withdraw;

import api.affiliate.api.affiliate.entity.StoreTable;
import api.affiliate.api.affiliate.entity.UserTable;
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
    private StoreTable storeTable;
    private UserTable userTable;

}
