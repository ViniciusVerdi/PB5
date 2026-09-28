package pb.estoque.inventory.application.usecase;

import org.springframework.stereotype.Service;
import pb.estoque.inventory.application.port.in.GetAllStockPortIn;
import pb.estoque.inventory.application.port.out.InventoryItemRepositoryPort;
import pb.estoque.inventory.domain.model.InventoryItem;

import java.util.List;

@Service
public class GetAllStockUseCase implements GetAllStockPortIn {

    private final InventoryItemRepositoryPort inventoryItemRepository;

    public GetAllStockUseCase(InventoryItemRepositoryPort inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    public List<InventoryItem> execute() {
        return inventoryItemRepository.findAll();
    }
}