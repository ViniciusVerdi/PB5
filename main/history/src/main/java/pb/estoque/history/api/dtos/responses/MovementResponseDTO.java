package pb.estoque.history.api.dtos.responses;

import lombok.Data;
import pb.estoque.history.domain.model.MovementType;

import java.time.Instant;
import java.util.UUID;

@Data
public class MovementResponseDTO {
    private UUID id;
    private UUID idProduct;
    private MovementType movementType;
    private Integer quantity;
    private Instant timestamp;
}
