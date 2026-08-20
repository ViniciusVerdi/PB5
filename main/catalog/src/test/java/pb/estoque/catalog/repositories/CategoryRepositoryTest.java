package pb.estoque.catalog.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pb.estoque.catalog.entities.Category;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Should save category when data is valid")
    void shouldSaveCategoryAndGenerateId() {
        Category category = new Category("Ferramentas", "Ferramentas de manutenção");

        Category saved = categoryRepository.save(category);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Ferramentas");
        assertThat(saved.getDescription()).isEqualTo("Ferramentas de manutenção");
    }
}
