package pb.estoque.catalog.domain.model;

import lombok.*;
import pb.estoque.catalog.domain.event.CategoryCreated;
import pb.estoque.catalog.shared.kernel.AggregateRoot;
import pb.estoque.catalog.shared.kernel.Name;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Category extends AggregateRoot {

    @EqualsAndHashCode.Include
    private UUID id;

    private Name name;

    private String description;

    private Instant createdAt;

    private Category(UUID id, Name name, String description, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public static Category create(Name name) {
        Category newCategory = new Category(UUID.randomUUID(), name, null, Instant.now());
        newCategory.eventRegister(new CategoryCreated(newCategory.id, name, null,newCategory.createdAt));
        return newCategory;
    }
    public static Category create(Name name, String description) {
        Category newCategory = new Category(UUID.randomUUID(), name, description, Instant.now());
        newCategory.eventRegister(new CategoryCreated(newCategory.id, name, description,newCategory.createdAt));
        return newCategory;
    }
    public static Category restore(UUID id, Name name, String description, Instant createdAt) {
        return new Category(id, name, description, createdAt);
    }

}

