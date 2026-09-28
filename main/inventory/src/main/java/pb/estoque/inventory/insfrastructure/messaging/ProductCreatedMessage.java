package pb.estoque.inventory.insfrastructure.messaging;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ProductCreatedMessage(UUID id, String name, BigDecimal price, UUID idCategory, Instant timestamp) {
}
