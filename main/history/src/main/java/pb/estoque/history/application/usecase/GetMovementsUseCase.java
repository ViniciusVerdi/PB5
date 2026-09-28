package pb.estoque.history.application.usecase;

import org.springframework.stereotype.Service;
import pb.estoque.history.application.port.in.GetMovementsPortIn;
import pb.estoque.history.application.port.out.MovementRecordRepositoryPort;
import pb.estoque.history.domain.model.MovementRecord;

import java.util.List;

@Service
public class GetMovementsUseCase implements GetMovementsPortIn {

    private final MovementRecordRepositoryPort movementRecordRepository;

    public GetMovementsUseCase(MovementRecordRepositoryPort movementRecordRepository) {
        this.movementRecordRepository = movementRecordRepository;
    }

    @Override
    public List<MovementRecord> execute() {
        return movementRecordRepository.findAll();
    }
}
