package pb.estoque.catalog.infrastructure.persistence;

import org.springframework.stereotype.Component;
import pb.estoque.catalog.application.port.out.ProductRepositoryPort;
import pb.estoque.catalog.domain.model.Product;
import pb.estoque.catalog.shared.kernel.MonetaryValue;
import pb.estoque.catalog.shared.kernel.Name;

import java.util.List;
import java.util.UUID;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {
    private final ProductRepository productRepository;

    public ProductRepositoryAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity saved = productRepository.save(toEntity(product));
        return toDomain(saved);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public boolean existsById(UUID id) {
        return productRepository.existsById(id);
    }

    @Override
    public boolean existsByNameAndCategoryId(String name, UUID idCategory) {
        return productRepository.existsByNameAndIdCategory(name, idCategory);
    }

    private ProductEntity toEntity(Product p) {
        return new ProductEntity(p.getId(), p.getName().value(), p.getPrice().value(), p.getIdCategory(), p.getCreatedAt());
    }
    private Product toDomain(ProductEntity e) {
        return Product.restore(e.getId(), new Name(e.getName()), new MonetaryValue(e.getPrice()), e.getIdCategory(), e.getCreatedAt());
    }
}
