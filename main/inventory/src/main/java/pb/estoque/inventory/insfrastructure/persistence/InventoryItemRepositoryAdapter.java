package pb.estoque.inventory.insfrastructure.persistence;

import org.springframework.stereotype.Component;
import pb.estoque.inventory.application.port.out.InventoryItemRepositoryPort;
import pb.estoque.inventory.domain.model.InventoryItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class InventoryItemRepositoryAdapter implements InventoryItemRepositoryPort {

    private final InventoryItemRepository repository;

    public InventoryItemRepositoryAdapter(InventoryItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public InventoryItem save(InventoryItem item) {
        InventoryItemEntity saved = repository.save(toEntity(item));
        return toDomain(saved);
    }

    @Override
    public Optional<InventoryItem> findByProduct(UUID idProduct) {
        return repository.findByIdProduct(idProduct).map(this::toDomain);
    }

    @Override
    public List<InventoryItem> findAll() {
        return repository.findAll().stream().map(this::toDomain).toList();
    }

    private InventoryItemEntity toEntity(InventoryItem item) {
        return new InventoryItemEntity(item.getId(), item.getIdProduct(), item.getQuantity(), item.getCreatedAt());
    }

    private InventoryItem toDomain(InventoryItemEntity e) {
        return InventoryItem.restore(e.getId(), e.getIdProduct(), e.getQuantity(), e.getCreatedAt());
    }
}
