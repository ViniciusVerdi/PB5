package pb.estoque.catalog.application.port.in;

import pb.estoque.catalog.application.command.CreateProductCommand;
import pb.estoque.catalog.domain.model.Product;

public interface CreateProductPortIn {
    Product execute(CreateProductCommand command);
}
