package pb.estoque.history.domain.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pb.estoque.history.shared.kernel.AggregateRoot;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MovementRecord extends AggregateRoot {

    @EqualsAndHashCode.Include
    private UUID id;

    private UUID idProduct;

    private MovementType movementType;

    private Integer quantity;

    private Instant timestamp;

    private MovementRecord(UUID id, UUID idProduct, MovementType movementType, Integer quantity, Instant timestamp) {
        this.id = id;
        this.idProduct = idProduct;
        this.movementType = movementType;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }

    public static MovementRecord create(UUID idProduct, MovementType movementType, Integer quantity) {
        if (idProduct == null || movementType == null || quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Erro: Dados de movimentação inválidos!");
        }
        return new MovementRecord(UUID.randomUUID(), idProduct, movementType, quantity, Instant.now());
    }

    public static MovementRecord restore(UUID id, UUID idProduct, MovementType movementType, Integer quantity, Instant timestamp) {
        return new MovementRecord(id, idProduct, movementType, quantity, timestamp);
    }
}
