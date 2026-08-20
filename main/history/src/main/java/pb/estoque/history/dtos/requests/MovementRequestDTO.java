package pb.estoque.history.dtos.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class MovementRequestDTO {
    private UUID idProduct;
    private Integer quantity;
}
