package pb.estoque.inventory.application.command;

import java.util.UUID;

public record ProcessOutboundCommand(UUID idProduct, Integer amount) {
}
