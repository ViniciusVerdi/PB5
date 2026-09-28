package pb.estoque.catalog.application.port.out;

import pb.estoque.catalog.domain.model.Category;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepositoryPort {
    Category save(Category category);
    Optional<Category> findById(UUID id);
}


