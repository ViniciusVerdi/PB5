package pb.estoque.history.application.command;

import pb.estoque.history.domain.model.MovementType;

import java.util.UUID;

public record RecordMovementCommand(UUID idProduct, MovementType movementType, Integer quantity) {
}
