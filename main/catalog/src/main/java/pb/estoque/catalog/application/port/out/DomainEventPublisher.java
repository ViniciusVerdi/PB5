package pb.estoque.catalog.application.port.out;

import pb.estoque.catalog.shared.kernel.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
