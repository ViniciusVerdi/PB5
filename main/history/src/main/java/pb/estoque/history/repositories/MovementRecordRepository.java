package pb.estoque.history.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pb.estoque.history.entities.MovementRecord;

import java.util.UUID;

public interface MovementRecordRepository extends JpaRepository<MovementRecord, UUID> {
}