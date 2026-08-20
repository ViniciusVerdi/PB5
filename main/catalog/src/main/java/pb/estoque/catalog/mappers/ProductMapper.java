package pb.estoque.catalog.mappers;

import org.mapstruct.Mapper;
import pb.estoque.catalog.dtos.responses.ProductResponseDTO;
import pb.estoque.catalog.entities.Product;
import java.util.List;

@Mapper(componentModel = "spring" , uses = {CategoryMapper.class})
public interface ProductMapper {

    ProductResponseDTO toDTO(Product produto);

    List<ProductResponseDTO> toDTO(List<Product> products);

}
