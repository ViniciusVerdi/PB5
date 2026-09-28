package pb.estoque.inventory.insfrastructure.messaging;

import java.time.Instant;
import java.util.UUID;

public record StockOutboundMessage(UUID id, UUID idProduct, Integer amount, Instant timestamp) {
}

