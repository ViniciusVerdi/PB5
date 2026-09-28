package pb.estoque.inventory.application.port.in;

import pb.estoque.inventory.application.command.CreateInventoryItemCommand;
import pb.estoque.inventory.domain.model.InventoryItem;

public interface CreateInventoryItemPortIn {
    InventoryItem execute(CreateInventoryItemCommand createInventoryItemCommand);
}
