package pb.estoque.catalog.infrastructure.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "id_category", nullable = false)
    private UUID idCategory;

    @Column(nullable = false)
    private Instant createdAt;

    public ProductEntity(UUID id,String name, BigDecimal price, UUID idCategory, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.idCategory = idCategory;
        this.createdAt = createdAt;
    }
}
