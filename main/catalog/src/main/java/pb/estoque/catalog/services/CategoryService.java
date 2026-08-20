package pb.estoque.catalog.services;

import org.springframework.stereotype.Service;
import pb.estoque.catalog.dtos.requests.CategoryRequestDTO;
import pb.estoque.catalog.dtos.responses.CategoryResponseDTO;
import pb.estoque.catalog.entities.Category;
import pb.estoque.catalog.mappers.CategoryMapper;
import pb.estoque.catalog.repositories.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository,CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryResponseDTO addCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category(categoryRequestDTO.getName(), categoryRequestDTO.getDescription());
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }
}
