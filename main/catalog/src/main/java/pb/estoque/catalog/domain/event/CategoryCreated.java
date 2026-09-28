package pb.estoque.catalog.domain.event;

import pb.estoque.catalog.shared.kernel.DomainEvent;
import pb.estoque.catalog.shared.kernel.Name;

import java.time.Instant;
import java.util.UUID;

public record CategoryCreated(UUID id, Name name, String description, Instant timestamp) implements DomainEvent {
}
