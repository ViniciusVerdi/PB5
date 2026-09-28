package pb.estoque.inventory.domain.event;

import pb.estoque.inventory.shared.kernel.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public record InventoryItemSubtractedQuantity(UUID id, UUID idProduct, Integer amount, Instant timestamp) implements DomainEvent {
}
