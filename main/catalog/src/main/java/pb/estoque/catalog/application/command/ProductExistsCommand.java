package pb.estoque.catalog.application.command;

import java.util.UUID;

public record ProductExistsCommand(UUID idProduct) {
}
