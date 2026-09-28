package pb.estoque.catalog.application.usecase;

import org.springframework.stereotype.Service;
import pb.estoque.catalog.application.command.CreateCategoryCommand;
import pb.estoque.catalog.application.port.in.CreateCategoryPortIn;
import pb.estoque.catalog.application.port.out.CategoryRepositoryPort;
import pb.estoque.catalog.application.port.out.DomainEventPublisher;
import pb.estoque.catalog.domain.model.Category;
import pb.estoque.catalog.shared.kernel.Name;

@Service
public class CreateCategoryUseCase implements CreateCategoryPortIn {
    private final CategoryRepositoryPort categoryRepositoryPort;
    private final DomainEventPublisher domainEventPublisher;

    public CreateCategoryUseCase(CategoryRepositoryPort categoryRepositoryPort, DomainEventPublisher domainEventPublisher) {
        this.categoryRepositoryPort = categoryRepositoryPort;
        this.domainEventPublisher = domainEventPublisher;
    }

    public Category execute(CreateCategoryCommand command) {
        Name name = new Name(command.name());
        Category category = Category.create(name, command.description());
        Category saved = categoryRepositoryPort.save(category);
        category.getEvents().forEach(domainEventPublisher::publish);
        return saved;
    }
}
