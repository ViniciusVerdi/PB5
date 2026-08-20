package pb.estoque.catalog.dtos.responses;

import lombok.Data;
import java.util.UUID;

@Data
public class CategoryResponseDTO {
    private UUID id;
    private String name;
    private String description;
}