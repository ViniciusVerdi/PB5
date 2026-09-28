package pb.estoque.catalog.application.port.out;

import pb.estoque.catalog.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepositoryPort {
    Product save(Product product);

    List<Product> findAll();

    boolean existsById(UUID id);

    boolean existsByNameAndCategoryId(String name, UUID idCategory);
}