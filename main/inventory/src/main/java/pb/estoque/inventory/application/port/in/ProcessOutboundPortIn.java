package pb.estoque.inventory.application.port.in;

import pb.estoque.inventory.application.command.ProcessOutboundCommand;
import pb.estoque.inventory.domain.model.InventoryItem;

public interface ProcessOutboundPortIn {
    InventoryItem execute (ProcessOutboundCommand processOutboundCommand);
}
