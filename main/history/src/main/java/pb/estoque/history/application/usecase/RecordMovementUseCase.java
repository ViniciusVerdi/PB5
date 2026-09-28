package pb.estoque.history.application.usecase;

import org.springframework.stereotype.Service;
import pb.estoque.history.application.command.RecordMovementCommand;
import pb.estoque.history.application.port.in.RecordMovementPortIn;
import pb.estoque.history.application.port.out.MovementRecordRepositoryPort;
import pb.estoque.history.domain.model.MovementRecord;

@Service
public class RecordMovementUseCase implements RecordMovementPortIn {

    private final MovementRecordRepositoryPort movementRecordRepository;

    public RecordMovementUseCase(MovementRecordRepositoryPort movementRecordRepository) {
        this.movementRecordRepository = movementRecordRepository;
    }

    @Override
    public MovementRecord execute(RecordMovementCommand command) {
        MovementRecord record = MovementRecord.create(command.idProduct(), command.movementType(), command.quantity());
        return movementRecordRepository.save(record);
    }
}
