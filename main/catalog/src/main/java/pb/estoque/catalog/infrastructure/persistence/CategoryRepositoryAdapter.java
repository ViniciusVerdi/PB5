package pb.estoque.catalog.infrastructure.persistence;

import org.springframework.stereotype.Component;
import pb.estoque.catalog.application.port.out.CategoryRepositoryPort;
import pb.estoque.catalog.domain.model.Category;
import pb.estoque.catalog.shared.kernel.Name;

import java.util.Optional;
import java.util.UUID;

@Component
public class CategoryRepositoryAdapter implements CategoryRepositoryPort {
    private final CategoryRepository categoryRepository;

    public CategoryRepositoryAdapter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        CategoryEntity saved = categoryRepository.save(toEntity(category));
        return toDomain(saved);
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id).map(this::toDomain);
    }

    private CategoryEntity toEntity(Category category) {
        return new CategoryEntity(category.getId(), category.getName().value(), category.getDescription(), category.getCreatedAt());
    }

    private Category toDomain(CategoryEntity entity) {
        return Category.restore(entity.getId(), new Name(entity.getName()), entity.getDescription(), entity.getCreatedAt());
    }
}
