package api.affiliate.api.affiliate.model.order;

import api.affiliate.api.affiliate.entity.OrderDetailTable;
import api.affiliate.api.affiliate.entity.WithdrawTable;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderResponse {

    private Integer orderListId;

    private Date date;

    private String image;

    private String status;

    private String userId;

    private String fullName;

    private String tel;

    private String address;

    private String sub;

    private String district;

    private String province;

    private Integer postalCode;

    private Integer totalPrice;

    private Integer storeId;

    private List<OrderDetailTable> detail;

    private List<WithdrawTable> withdraw;
}
