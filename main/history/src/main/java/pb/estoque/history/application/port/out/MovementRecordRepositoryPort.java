package pb.estoque.history.application.port.out;

import pb.estoque.history.domain.model.MovementRecord;

import java.util.List;

public interface MovementRecordRepositoryPort {
    MovementRecord save(MovementRecord record);

    List<MovementRecord> findAll();
}
