package pb.estoque.inventory.application.command;

import java.util.UUID;

public record GetStockCommand(UUID idProduct) {
}
