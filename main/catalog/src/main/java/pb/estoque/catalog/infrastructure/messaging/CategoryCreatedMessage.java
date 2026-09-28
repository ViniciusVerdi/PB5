package pb.estoque.catalog.infrastructure.messaging;

import java.time.Instant;
import java.util.UUID;

public record CategoryCreatedMessage(UUID id, String name, String description, Instant timestamp) {
}
