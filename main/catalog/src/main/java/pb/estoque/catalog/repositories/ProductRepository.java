package pb.estoque.catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pb.estoque.catalog.entities.Product;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByNameAndCategoryId(String name, UUID Idcategory);

}
