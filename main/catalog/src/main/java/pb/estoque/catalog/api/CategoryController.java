package pb.estoque.catalog.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.estoque.catalog.api.dtos.requests.CategoryRequestDTO;
import pb.estoque.catalog.api.dtos.responses.CategoryResponseDTO;
import pb.estoque.catalog.api.mapper.CategoryMapper;
import pb.estoque.catalog.application.command.CreateCategoryCommand;
import pb.estoque.catalog.application.port.in.CreateCategoryPortIn;
import pb.estoque.catalog.domain.model.Category;

@RestController
@RequestMapping("/categoria")
public class CategoryController {
    private final CreateCategoryPortIn createCategoryPortIn;
    private final CategoryMapper categoryMapper;

    public CategoryController(CreateCategoryPortIn createCategoryPortIn, CategoryMapper categoryMapper) {
        this.createCategoryPortIn = createCategoryPortIn;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody CategoryRequestDTO dto) {
        CreateCategoryCommand command = new CreateCategoryCommand(dto.getName(), dto.getDescription());
        Category category = createCategoryPortIn.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.toResponse(category));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
