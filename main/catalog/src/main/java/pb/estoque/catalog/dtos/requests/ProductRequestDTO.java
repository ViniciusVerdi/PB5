package pb.estoque.catalog.dtos.requests;

import lombok.Data;
import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductRequestDTO {
    private String name;
    private BigDecimal price;
    private UUID idCategory;

}
