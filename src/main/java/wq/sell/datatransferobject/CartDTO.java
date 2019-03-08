package wq.sell.datatransferobject;

import lombok.Data;

@Data
public class CartDTO {
    private String productId;
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
