package pb.estoque.inventory.domain.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pb.estoque.inventory.domain.event.InventoryItemAddedQuantity;
import pb.estoque.inventory.domain.event.InventoryItemCreated;
import pb.estoque.inventory.domain.event.InventoryItemSubtractedQuantity;
import pb.estoque.inventory.shared.kernel.AggregateRoot;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class InventoryItem extends AggregateRoot {

    @EqualsAndHashCode.Include
    private UUID id;

    private UUID idProduct;

    private Integer quantity;

    private Instant createdAt;

    private InventoryItem (UUID id, UUID idProduct, Integer quantity, Instant createdAt) {
        this.id = id;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }
    public static InventoryItem create(UUID idProduct) {
        InventoryItem newItem = new InventoryItem(UUID.randomUUID(), idProduct, 0, Instant.now());
        newItem.eventRegister(new InventoryItemCreated(newItem.id, idProduct, 0, newItem.createdAt));
        return newItem;
    }
    public void addQuantity(Integer amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Erro: A quantidade a ser adicionada deve ser maior que zero!");
        }
        this.quantity += amount;
        eventRegister(new InventoryItemAddedQuantity(id, idProduct, amount, Instant.now()));
    }

    public void subtractQuantity(Integer amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Erro: A quantidade a ser subtraída deve ser maior que zero!");
        }
        if (this.quantity < amount) {
            throw new IllegalStateException("Erro: Estoque insuficiente!");
        }
        this.quantity -= amount;
        eventRegister(new InventoryItemSubtractedQuantity(id, idProduct, amount, Instant.now()));
    }

    public static InventoryItem restore(UUID id, UUID idProduct, Integer quantity, Instant createdAt) {
        return new InventoryItem(id, idProduct, quantity, createdAt);
    }
}
