package pb.estoque.inventory.application.port.out;

import pb.estoque.inventory.shared.kernel.DomainEvent;

public interface DomainEventPublisherPort {
    void publish(DomainEvent event);
}
