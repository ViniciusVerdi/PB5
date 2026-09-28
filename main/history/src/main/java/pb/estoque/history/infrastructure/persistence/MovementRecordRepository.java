package pb.estoque.history.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovementRecordRepository extends JpaRepository<MovementRecordEntity, UUID> {
}
