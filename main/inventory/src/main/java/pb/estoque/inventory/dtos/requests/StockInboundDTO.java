package pb.estoque.inventory.dtos.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class StockInboundDTO {
    private UUID idProduct;
    private Integer quantity;
}