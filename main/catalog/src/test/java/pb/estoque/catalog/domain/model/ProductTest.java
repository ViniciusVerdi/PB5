package pb.estoque.catalog.domain.model;

import org.junit.jupiter.api.Test;
import pb.estoque.catalog.shared.kernel.MonetaryValue;
import pb.estoque.catalog.shared.kernel.Name;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {

    @Test
    void shouldCreateProductWithGeneratedIdAndEvent() {
        UUID idCategory = UUID.randomUUID();
        Product product = Product.create(new Name("Furadeira"), MonetaryValue.of("199.90"), idCategory);

        assertThat(product.getId()).isNotNull();
        assertThat(product.getName().value()).isEqualTo("Furadeira");
        assertThat(product.getPrice().value()).isEqualByComparingTo("199.90");
        assertThat(product.getIdCategory()).isEqualTo(idCategory);
        assertThat(product.getCreatedAt()).isNotNull();
        assertThat(product.getEvents()).hasSize(1);
    }
}
