package pb.estoque.history.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MovementRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "id_product", nullable = false)
    private UUID productId;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", nullable = false, length = 20)
    private MovementType movementType;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public MovementRecord(UUID productId, MovementType movementType, Integer quantity) {
        if (productId == null || movementType == null || quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Erro: Dados de movimentação inválidos!");
        }
        this.productId = productId;
        this.movementType = movementType;
        this.quantity = quantity;
        this.timestamp = LocalDateTime.now();
    }
}
