package pb.estoque.inventory.insfrastructure.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InventoryItemEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "id_product", nullable = false, unique = true)
    private UUID idProduct;

    @Column(nullable = false)
    private Integer quantity;

    private Instant createdAt;

    public InventoryItemEntity(UUID id,UUID idProduct, Integer quantity, Instant createdAt) {
        this.id = id;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }
}