package pb.estoque.inventory.application.port.in;

import pb.estoque.inventory.application.command.ProcessInboundCommand;
import pb.estoque.inventory.domain.model.InventoryItem;

public interface ProcessInboundPortIn {
    InventoryItem execute(ProcessInboundCommand processInboundCommand);
}
