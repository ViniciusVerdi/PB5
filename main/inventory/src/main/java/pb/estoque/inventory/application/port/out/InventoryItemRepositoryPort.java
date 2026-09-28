package pb.estoque.inventory.application.port.out;

import pb.estoque.inventory.domain.model.InventoryItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryItemRepositoryPort {

    InventoryItem save(InventoryItem item);

    Optional<InventoryItem> findByProduct(UUID idProduct);

    List<InventoryItem> findAll();
}
