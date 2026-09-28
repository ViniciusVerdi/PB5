package pb.estoque.catalog.application.usecase;

import org.springframework.stereotype.Service;
import pb.estoque.catalog.application.command.CreateProductCommand;
import pb.estoque.catalog.application.port.in.CreateProductPortIn;
import pb.estoque.catalog.application.port.out.CategoryRepositoryPort;
import pb.estoque.catalog.application.port.out.DomainEventPublisher;
import pb.estoque.catalog.application.port.out.ProductRepositoryPort;
import pb.estoque.catalog.domain.model.Product;
import pb.estoque.catalog.shared.kernel.MonetaryValue;
import pb.estoque.catalog.shared.kernel.Name;

@Service
public class CreateProductUseCase implements CreateProductPortIn {
    private final ProductRepositoryPort productRepository;
    private final CategoryRepositoryPort categoryRepository;
    private final DomainEventPublisher eventPublisher;

    public CreateProductUseCase(ProductRepositoryPort productRepository, CategoryRepositoryPort categoryRepository, DomainEventPublisher eventPublisher) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.eventPublisher = eventPublisher;
    }

    public Product execute(CreateProductCommand command) {
        if (productRepository.existsByNameAndCategoryId(command.name(), command.idCategory())) {
            throw new IllegalStateException("Erro: Produto já cadastrado!");
        }
        if (categoryRepository.findById(command.idCategory()).isEmpty()) {
            throw new IllegalArgumentException("Erro: Categoria não encontrada!");
        }

        Name name = new Name(command.name());
        MonetaryValue price = new MonetaryValue(command.price());
        Product product = Product.create(name, price, command.idCategory());

        Product saved = productRepository.save(product);
        product.getEvents().forEach(eventPublisher::publish);
        return saved;
    }
}
