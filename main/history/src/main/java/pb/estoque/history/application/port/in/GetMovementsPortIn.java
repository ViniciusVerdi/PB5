package pb.estoque.history.application.port.in;

import pb.estoque.history.domain.model.MovementRecord;

import java.util.List;

public interface GetMovementsPortIn {
    List<MovementRecord> execute();
}
