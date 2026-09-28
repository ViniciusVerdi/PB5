package pb.estoque.catalog.domain.event;

import pb.estoque.catalog.shared.kernel.DomainEvent;
import pb.estoque.catalog.shared.kernel.MonetaryValue;
import pb.estoque.catalog.shared.kernel.Name;

import java.time.Instant;
import java.util.UUID;

public record ProductCreated(UUID id, Name name, MonetaryValue price, UUID idCategory, Instant timestamp) implements DomainEvent {
}
