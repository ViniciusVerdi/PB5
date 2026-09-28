package pb.estoque.history.infrastructure.messaging;

import java.time.Instant;
import java.util.UUID;

public record StockInboundMessage(UUID id, UUID idProduct, Integer amount, Instant timestamp) {
}
