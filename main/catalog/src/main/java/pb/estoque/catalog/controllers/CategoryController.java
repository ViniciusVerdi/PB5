package pb.estoque.catalog.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.estoque.catalog.dtos.requests.CategoryRequestDTO;
import pb.estoque.catalog.dtos.responses.CategoryResponseDTO;
import pb.estoque.catalog.services.CategoryService;

@RestController
@RequestMapping("/categoria")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;

    }

    @PostMapping("/adicionar")
    public ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody CategoryRequestDTO requestDTO) {
        CategoryResponseDTO responseDTO =this.categoryService.addCategory(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
