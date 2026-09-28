package pb.estoque.inventory.shared.kernel;

import java.time.Instant;

public interface DomainEvent {
    Instant timestamp();
}
