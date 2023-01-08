package api.affiliate.api.affiliate.model.link;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class LinkResponse {

    private String userName;
    private Integer amount;
    private Float price;
    private Date createDate;
    private String linkId;
    private Float totalPrice;

    @JsonIgnore
    private Long amountWithdraw;
    public LinkResponse(String userName, Integer amount, Float price, Date createDate, String linkId) {
        this.userName = userName;
        this.amount = amount;
        this.price = price;
        this.totalPrice = price * amount;
        this.createDate = createDate;
        this.linkId = linkId;
    }

//    public LinkResponse(String userName, Long amount, Float price, Date createDate, String linkId,Long amountWithdraw) {
//        this.userName = userName;
//        this.amount = amount;
//        this.amountWithdraw = amountWithdraw;
//        this.totalPrice = price * (amount-amountWithdraw);
//        this.createDate = createDate;
//        this.linkId = linkId;
//    }

}
