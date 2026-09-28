package pb.estoque.catalog.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    boolean existsByNameAndIdCategory(String name, UUID idCategory);

}
