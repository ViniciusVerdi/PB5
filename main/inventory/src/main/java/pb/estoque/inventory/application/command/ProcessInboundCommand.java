package pb.estoque.inventory.application.command;

import java.util.UUID;

public record ProcessInboundCommand(UUID idProduct,Integer amount) {
}
