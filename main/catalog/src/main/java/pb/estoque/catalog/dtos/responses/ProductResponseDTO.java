package pb.estoque.catalog.dtos.responses;

import lombok.Data;
import java.util.UUID;

@Data
public class ProductResponseDTO {
    private UUID id;
    private String name;
    private java.math.BigDecimal price;
    private CategoryResponseDTO category;
}
