package api.affiliate.api.affiliate.model.store;

import lombok.Data;

@Data
public class TotalPriceResponse {
    private Long total;
    private Float totalBeforeCommission;

    public TotalPriceResponse(Long total) {
        this.total = total;
    }
}
