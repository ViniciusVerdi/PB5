package pb.estoque.inventory.insfrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryItemRepository extends JpaRepository<InventoryItemEntity, UUID> {
    Optional<InventoryItemEntity> findByIdProduct(UUID idProduct);
}
