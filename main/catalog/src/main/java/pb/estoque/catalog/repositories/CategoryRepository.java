package pb.estoque.catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pb.estoque.catalog.entities.Category;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {}
