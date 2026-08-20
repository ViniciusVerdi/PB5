package pb.estoque.catalog.mappers;

import org.mapstruct.Mapper;
import pb.estoque.catalog.dtos.responses.CategoryResponseDTO;
import pb.estoque.catalog.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponseDTO toResponse(Category category);
}
