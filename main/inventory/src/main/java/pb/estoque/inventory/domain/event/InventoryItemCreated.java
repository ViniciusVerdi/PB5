package pb.estoque.inventory.domain.event;

import pb.estoque.inventory.shared.kernel.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record InventoryItemCreated(UUID id, UUID idProduct, Integer quantity, Instant timestamp) implements DomainEvent {
}
