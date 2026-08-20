package pb.estoque.history.services;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pb.estoque.history.entities.MovementRecord;
import pb.estoque.history.entities.MovementType;
import pb.estoque.history.repositories.MovementRecordRepository;

@Service
public class MovementHistoryService {

    private final MovementRecordRepository movementRecordRepository;

    public MovementHistoryService(
        MovementRecordRepository movementRecordRepository
    ) {
        this.movementRecordRepository = movementRecordRepository;
    }

    @Transactional
    public void recordMovement(
        UUID productId,
        MovementType type,
        Integer quantity
    ) {
        MovementRecord record = new MovementRecord(productId, type, quantity);
        movementRecordRepository.save(record);
    }
}
