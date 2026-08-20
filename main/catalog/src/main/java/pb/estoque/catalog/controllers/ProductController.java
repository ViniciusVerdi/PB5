package pb.estoque.catalog.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.estoque.catalog.dtos.requests.ProductRequestDTO;
import pb.estoque.catalog.dtos.responses.ProductResponseDTO;
import pb.estoque.catalog.services.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> productsDTO = this.productService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(productsDTO);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = this.productService.addProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDTO);
    }

    @GetMapping("/id")
    public ResponseEntity<Boolean> getProductExists(@RequestParam UUID id) {
        Boolean exists =  productService.existsById(id);
        return ResponseEntity.status(HttpStatus.OK).body(exists);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
