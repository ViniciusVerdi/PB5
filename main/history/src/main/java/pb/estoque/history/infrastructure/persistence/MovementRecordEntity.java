package pb.estoque.history.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.*;
import pb.estoque.history.domain.model.MovementType;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MovementRecordEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "id_product", nullable = false)
    private UUID idProduct;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", nullable = false, length = 20)
    private MovementType movementType;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Instant timestamp;

    public MovementRecordEntity(UUID id, UUID idProduct, MovementType movementType, Integer quantity, Instant timestamp) {
        this.id = id;
        this.idProduct = idProduct;
        this.movementType = movementType;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }
}
