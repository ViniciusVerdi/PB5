package pb.estoque.inventory.shared.kernel;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRoot {
    private final List<DomainEvent> events = new ArrayList<>();

    protected void eventRegister(DomainEvent event) {
        events.add(event);
    }
    public List<DomainEvent> getEvents() {
        return List.copyOf(events);
    }

    public void ClearEvents() {
        events.clear();
    }

}
