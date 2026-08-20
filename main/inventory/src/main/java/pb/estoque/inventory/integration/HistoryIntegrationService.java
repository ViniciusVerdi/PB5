package pb.estoque.inventory.integration;

import java.util.UUID;

public interface HistoryIntegrationService {
    void recordInbound(UUID idProduct, Integer quantity);

    void recordOutbound(UUID idProduct, Integer quantity);
}
