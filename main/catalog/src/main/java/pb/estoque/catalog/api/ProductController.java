package pb.estoque.catalog.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.estoque.catalog.api.dtos.requests.ProductRequestDTO;
import pb.estoque.catalog.api.dtos.responses.ProductResponseDTO;
import pb.estoque.catalog.api.mapper.ProductMapper;
import pb.estoque.catalog.application.command.CreateProductCommand;
import pb.estoque.catalog.application.port.in.CreateProductPortIn;
import pb.estoque.catalog.application.port.in.GetAllProductsPortIn;
import pb.estoque.catalog.application.port.in.ProductExistsPortIn;
import pb.estoque.catalog.domain.model.Product;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProductController {
    private final CreateProductPortIn createProductPortIn;
    private final GetAllProductsPortIn getAllProductsPortIn;
    private final ProductExistsPortIn productExistsPortIn;
    private final ProductMapper productMapper;

    public ProductController(CreateProductPortIn createProductPortIn,GetAllProductsPortIn getAllProductsPortIn,ProductExistsPortIn productExistsPortIn,ProductMapper productMapper) {
        this.createProductPortIn = createProductPortIn;
        this.getAllProductsPortIn = getAllProductsPortIn;
        this.productExistsPortIn = productExistsPortIn;
        this.productMapper = productMapper;
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = getAllProductsPortIn.getAll().stream()
                .map(productMapper::toDTO)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody ProductRequestDTO dto) {
        CreateProductCommand command = new CreateProductCommand(dto.getName(), dto.getPrice(), dto.getIdCategory());
        Product product = createProductPortIn.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toDTO(product));
    }

    @GetMapping("/id")
    public ResponseEntity<Boolean> getProductExists(@RequestParam UUID id) {
        boolean exists = productExistsPortIn.exists(id);
        return ResponseEntity.status(HttpStatus.OK).body(exists);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
