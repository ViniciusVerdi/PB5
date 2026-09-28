package pb.estoque.inventory.application.usecase;

import org.springframework.stereotype.Service;
import pb.estoque.inventory.application.command.ProcessInboundCommand;
import pb.estoque.inventory.application.port.in.ProcessInboundPortIn;
import pb.estoque.inventory.application.port.out.DomainEventPublisherPort;
import pb.estoque.inventory.application.port.out.InventoryItemRepositoryPort;
import pb.estoque.inventory.domain.model.InventoryItem;

@Service
public class ProcessInboundUseCase implements ProcessInboundPortIn {

    private final InventoryItemRepositoryPort inventoryItemRepository;
    private final DomainEventPublisherPort eventPublisher;

    public ProcessInboundUseCase(InventoryItemRepositoryPort inventoryItemRepository,
                                 DomainEventPublisherPort eventPublisher) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public InventoryItem execute(ProcessInboundCommand command) {
        InventoryItem item = inventoryItemRepository.findByProduct(command.idProduct())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Erro: o produto não está registrado no estoque!"
                ));

        item.addQuantity(command.amount());
        InventoryItem saved = inventoryItemRepository.save(item);
        item.getEvents().forEach(eventPublisher::publish);
        return saved;
    }
}