package pb.estoque.history.application.port.in;

import pb.estoque.history.application.command.RecordMovementCommand;
import pb.estoque.history.domain.model.MovementRecord;

public interface RecordMovementPortIn {
    MovementRecord execute(RecordMovementCommand command);
}
