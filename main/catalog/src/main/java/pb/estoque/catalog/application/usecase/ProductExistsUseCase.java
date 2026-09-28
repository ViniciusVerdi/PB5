package pb.estoque.catalog.application.usecase;

import org.springframework.stereotype.Service;
import pb.estoque.catalog.application.port.in.ProductExistsPortIn;
import pb.estoque.catalog.application.port.out.ProductRepositoryPort;

import java.util.UUID;

@Service
public class ProductExistsUseCase implements ProductExistsPortIn {
    private final ProductRepositoryPort productRepository;
    public ProductExistsUseCase(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }
    public boolean exists(UUID id) {
        return productRepository.existsById(id);
    }
}
