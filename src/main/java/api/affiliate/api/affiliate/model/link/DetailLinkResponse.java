package api.affiliate.api.affiliate.model.link;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data

@ToString
public class DetailLinkResponse {

    public DetailLinkResponse(Long totalAmount, Long totalPrice, Long totalLinkAmount) {
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
        this.linkAmount = 0;
        this.totalLinkAmount = totalLinkAmount;
    }

    private Long totalAmount;
    private Long totalPrice;
    private int linkAmount;
    private Long totalLinkAmount;
    private List<LinkResponse> links;


}
