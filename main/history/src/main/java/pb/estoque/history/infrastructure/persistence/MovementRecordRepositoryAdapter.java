package pb.estoque.history.infrastructure.persistence;

import java.util.List;
import org.springframework.stereotype.Component;
import pb.estoque.history.application.port.out.MovementRecordRepositoryPort;
import pb.estoque.history.domain.model.MovementRecord;

@Component
public class MovementRecordRepositoryAdapter
    implements MovementRecordRepositoryPort
{

    private final MovementRecordRepository repository;

    public MovementRecordRepositoryAdapter(
        MovementRecordRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public MovementRecord save(MovementRecord record) {
        MovementRecordEntity saved = repository.save(toEntity(record));
        return toDomain(saved);
    }

    @Override
    public List<MovementRecord> findAll() {
        return repository.findAll().stream().map(this::toDomain).toList();
    }

    private MovementRecordEntity toEntity(MovementRecord record) {
        return new MovementRecordEntity(
            record.getId(),
            record.getIdProduct(),
            record.getMovementType(),
            record.getQuantity(),
            record.getTimestamp()
        );
    }

    private MovementRecord toDomain(MovementRecordEntity entity) {
        return MovementRecord.restore(
            entity.getId(),
            entity.getIdProduct(),
            entity.getMovementType(),
            entity.getQuantity(),
            entity.getTimestamp()
        );
    }
}
