package pb.estoque.inventory.api.dtos.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class StockInboundDTO {
    private UUID idProduct;
    private Integer amount;
}