package pb.estoque.history.shared.kernel;

import java.time.Instant;

public interface DomainEvent {
    Instant timestamp();
}
