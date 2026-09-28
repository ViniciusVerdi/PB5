package pb.estoque.catalog.application.command;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductCommand(String name, BigDecimal price, UUID idCategory) {
}
