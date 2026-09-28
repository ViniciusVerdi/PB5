package pb.estoque.catalog.domain.model;

import org.junit.jupiter.api.Test;
import pb.estoque.catalog.shared.kernel.Name;

import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CategoryTest {

    @Test
    void shouldCreateCategoryWithGeneratedIdAndEvent() {
        Category category = Category.create(new Name("Ferramentas"), "Ferramentas de manutenção");

        assertThat(category.getId()).isNotNull();
        assertThat(category.getName().value()).isEqualTo("Ferramentas");
        assertThat(category.getDescription()).isEqualTo("Ferramentas de manutenção");
        assertThat(category.getCreatedAt()).isNotNull();
        assertThat(category.getEvents()).hasSize(1);
    }

    @Test
    void shouldRestoreCategoryWithoutEvents() {
        Category category = Category.restore(UUID.randomUUID(), new Name("Ferramentas"), "descrição", Instant.now());

        assertThat(category.getId()).isNotNull();
        assertThat(category.getEvents()).isEmpty();
    }

    @Test
    void shouldRejectBlankName() {
        assertThatThrownBy(() -> new Name("   "))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
