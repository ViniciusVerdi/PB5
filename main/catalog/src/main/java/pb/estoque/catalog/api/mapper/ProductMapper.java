package pb.estoque.catalog.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pb.estoque.catalog.api.dtos.responses.ProductResponseDTO;
import pb.estoque.catalog.domain.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "name", expression = "java(product.getName().value())")
    @Mapping(target = "price", expression = "java(product.getPrice().value())")
    ProductResponseDTO toDTO(Product product);
}

