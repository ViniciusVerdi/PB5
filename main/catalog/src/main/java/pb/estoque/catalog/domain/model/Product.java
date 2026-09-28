package pb.estoque.catalog.domain.model;

import java.time.Instant;
import java.util.UUID;
import lombok.*;
import pb.estoque.catalog.domain.event.ProductCreated;
import pb.estoque.catalog.shared.kernel.AggregateRoot;
import pb.estoque.catalog.shared.kernel.MonetaryValue;
import pb.estoque.catalog.shared.kernel.Name;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product extends AggregateRoot {

    @EqualsAndHashCode.Include
    private UUID id;

    private Name name;

    private MonetaryValue price;

    private UUID idCategory;

    private Instant createdAt;

    private Product(UUID id, Name name, MonetaryValue price, UUID idCategory, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.idCategory = idCategory;
        this.createdAt = createdAt;
    }

    public static Product create(Name name, MonetaryValue price, UUID idCategory){
        Product newProduct = new Product(UUID.randomUUID(), name, price, idCategory,Instant.now());
        newProduct.eventRegister(new ProductCreated(newProduct.id,name,price,idCategory,newProduct.createdAt));
        return newProduct;
    }

    public static Product restore(UUID id, Name name, MonetaryValue price ,UUID idCategory, Instant createdAt){
       return new Product(id, name, price, idCategory,createdAt);
    }
}
