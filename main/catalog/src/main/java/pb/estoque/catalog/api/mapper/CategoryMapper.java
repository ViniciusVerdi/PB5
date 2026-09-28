package pb.estoque.catalog.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pb.estoque.catalog.api.dtos.responses.CategoryResponseDTO;
import pb.estoque.catalog.domain.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "name", expression = "java(category.getName().value())")
    CategoryResponseDTO toResponse(Category category);
}