package pb.estoque.catalog.shared.kernel;

import java.time.Instant;

public interface DomainEvent {
    Instant timestamp();
}
