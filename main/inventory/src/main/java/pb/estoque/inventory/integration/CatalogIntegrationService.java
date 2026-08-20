package pb.estoque.inventory.integration;

import java.util.UUID;

public interface CatalogIntegrationService {
    boolean productExists(UUID idProduct);
}