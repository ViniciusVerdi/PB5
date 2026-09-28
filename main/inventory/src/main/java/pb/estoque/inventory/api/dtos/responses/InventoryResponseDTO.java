package pb.estoque.inventory.api.dtos.responses;

import lombok.Data;
import java.util.UUID;

@Data
public class InventoryResponseDTO {
    private UUID idProduct;
    private Integer quantity;

    public InventoryResponseDTO(UUID idProduct, Integer quantity) {
        this.idProduct = idProduct;
        this.quantity = quantity;
    }
}