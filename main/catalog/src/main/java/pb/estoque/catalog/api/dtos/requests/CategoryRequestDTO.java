package pb.estoque.catalog.api.dtos.requests;

import lombok.Data;

@Data
public class CategoryRequestDTO {
    private String name;
    private String description;
}
