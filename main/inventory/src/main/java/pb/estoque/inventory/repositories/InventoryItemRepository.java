package pb.estoque.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pb.estoque.inventory.entities.InventoryItem;

import java.util.Optional;
import java.util.UUID;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, UUID> {
    Optional<InventoryItem> findByIdProduct(UUID idProduct);
}
