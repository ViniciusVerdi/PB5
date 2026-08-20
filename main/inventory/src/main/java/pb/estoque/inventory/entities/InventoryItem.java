package pb.estoque.inventory.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "id_product", nullable = false, unique = true)
    private UUID idProduct;

    @Column(nullable = false)
    private Integer quantity;

    public InventoryItem(UUID idProduct) {
        if (idProduct == null) {
            throw new IllegalArgumentException("Erro: O produto é obrigatório!");
        }
        this.idProduct = idProduct;
        this.quantity = 0;
    }

    public void addQuantity(Integer amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Erro: A quantidade a ser adicionada deve ser maior que zero!");
        }
        this.quantity += amount;
    }

    public void subtractQuantity(Integer amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Erro: A quantidade a ser subtraida deve ser maior que zero!");
        }
        if (this.quantity < amount) {
            throw new IllegalStateException("Erro: Estoque insuficiente!");
        }
        this.quantity -= amount;
    }
}