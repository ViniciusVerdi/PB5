package pb.estoque.inventory.application.usecase;

import org.springframework.stereotype.Service;
import pb.estoque.inventory.application.command.CreateInventoryItemCommand;
import pb.estoque.inventory.application.port.in.CreateInventoryItemPortIn;
import pb.estoque.inventory.application.port.out.InventoryItemRepositoryPort;
import pb.estoque.inventory.domain.model.InventoryItem;

@Service
public class CreateInventoryItemUseCase implements CreateInventoryItemPortIn {

    private final InventoryItemRepositoryPort inventoryItemRepository;

    public CreateInventoryItemUseCase(InventoryItemRepositoryPort inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    public InventoryItem execute(CreateInventoryItemCommand command) {
        return inventoryItemRepository.findByProduct(command.idProduct())
                .orElseGet(() -> {
                    InventoryItem item = InventoryItem.create(command.idProduct());
                    return inventoryItemRepository.save(item);
                });
    }
}