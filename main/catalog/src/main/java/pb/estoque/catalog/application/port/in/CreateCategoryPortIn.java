package pb.estoque.catalog.application.port.in;

import pb.estoque.catalog.application.command.CreateCategoryCommand;
import pb.estoque.catalog.domain.model.Category;

public interface CreateCategoryPortIn {
    Category execute(CreateCategoryCommand command);
}
