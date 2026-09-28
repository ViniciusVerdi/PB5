package pb.estoque.inventory.application.port.in;

import pb.estoque.inventory.domain.model.InventoryItem;

import java.util.List;

public interface GetAllStockPortIn {
    List<InventoryItem> execute();
}
