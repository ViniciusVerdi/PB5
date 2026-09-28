package pb.estoque.inventory.application.usecase;

import org.springframework.stereotype.Service;
import pb.estoque.inventory.application.command.GetStockCommand;
import pb.estoque.inventory.application.port.in.GetStockPortIn;
import pb.estoque.inventory.application.port.out.InventoryItemRepositoryPort;
import pb.estoque.inventory.domain.model.InventoryItem;

@Service
public class GetStockUseCase implements GetStockPortIn {

    private final InventoryItemRepositoryPort inventoryItemRepository;

    public GetStockUseCase(InventoryItemRepositoryPort inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    @Override
    public InventoryItem execute(GetStockCommand command) {
        return inventoryItemRepository.findByProduct(command.idProduct())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Erro: o produto não está registrado no estoque!"
                ));
    }
}