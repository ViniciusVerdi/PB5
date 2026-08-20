package pb.estoque.inventory.dtos.requests;

import lombok.Data;
import java.util.UUID;

@Data
public class StockOutboundDTO {
    private UUID idProduct;
    private Integer quantity;
}