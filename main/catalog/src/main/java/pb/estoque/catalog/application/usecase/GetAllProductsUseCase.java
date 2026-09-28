package pb.estoque.catalog.application.usecase;

import org.springframework.stereotype.Service;
import pb.estoque.catalog.application.port.in.GetAllProductsPortIn;
import pb.estoque.catalog.application.port.out.ProductRepositoryPort;
import pb.estoque.catalog.domain.model.Product;

import java.util.List;

@Service
public class GetAllProductsUseCase implements GetAllProductsPortIn {
    private final ProductRepositoryPort productRepository;
    public GetAllProductsUseCase(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
