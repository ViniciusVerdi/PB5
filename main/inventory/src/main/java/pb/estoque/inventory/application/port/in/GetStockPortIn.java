package pb.estoque.inventory.application.port.in;

import pb.estoque.inventory.application.command.GetStockCommand;
import pb.estoque.inventory.domain.model.InventoryItem;

public interface GetStockPortIn {
    InventoryItem execute(GetStockCommand command);
}
