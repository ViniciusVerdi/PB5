package pb.estoque.inventory.services;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pb.estoque.inventory.dtos.requests.StockInboundDTO;
import pb.estoque.inventory.dtos.requests.StockOutboundDTO;
import pb.estoque.inventory.dtos.responses.InventoryResponseDTO;
import pb.estoque.inventory.entities.InventoryItem;
import pb.estoque.inventory.integration.CatalogIntegrationService;
import pb.estoque.inventory.integration.HistoryIntegrationService;
import pb.estoque.inventory.mappers.InventoryMapper;
import pb.estoque.inventory.repositories.InventoryItemRepository;

@Service
public class InventoryService {

    private final InventoryItemRepository inventoryItemRepository;
    private final CatalogIntegrationService catalogIntegrationService;
    private final HistoryIntegrationService historyIntegrationService;
    private final InventoryMapper inventoryMapper;

    public InventoryService(
        InventoryItemRepository inventoryItemRepository,
        CatalogIntegrationService catalogIntegrationService,
        HistoryIntegrationService historyIntegrationService,
        InventoryMapper inventoryMapper
    ) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.catalogIntegrationService = catalogIntegrationService;
        this.historyIntegrationService = historyIntegrationService;
        this.inventoryMapper = inventoryMapper;
    }

    @Transactional(readOnly = true)
    public InventoryResponseDTO getStock(UUID idProduct) {
        InventoryItem item = getItemOrThrow(idProduct);
        return new InventoryResponseDTO(
            item.getIdProduct(),
            item.getQuantity()
        );
    }

    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> getAll() {
        List<InventoryItem> items = inventoryItemRepository.findAll();
        return inventoryMapper.toListDTO(items);
    }

    @Transactional
    public void processInbound(StockInboundDTO dto) {
        InventoryItem item = getItemOrCreate(dto.getIdProduct());
        item.addQuantity(dto.getQuantity());

        inventoryItemRepository.save(item);
        historyIntegrationService.recordInbound(
            dto.getIdProduct(),
            dto.getQuantity()
        );
    }

    @Transactional
    public void processOutbound(StockOutboundDTO dto) {
        InventoryItem item = getItemOrThrow(dto.getIdProduct());
        item.subtractQuantity(dto.getQuantity());

        inventoryItemRepository.save(item);
        historyIntegrationService.recordOutbound(
            dto.getIdProduct(),
            dto.getQuantity()
        );
    }

    private InventoryItem getItemOrThrow(UUID idProduct) {
        return inventoryItemRepository
            .findByIdProduct(idProduct)
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "Produto não possui registro de estoque para esta operação"
                )
            );
    }

    private InventoryItem getItemOrCreate(UUID idProduct) {
        return inventoryItemRepository
            .findByIdProduct(idProduct)
            .orElseGet(() -> {
                if (!catalogIntegrationService.productExists(idProduct)) {
                    throw new IllegalArgumentException(
                        "Produto inexistente no catálogo"
                    );
                }
                return new InventoryItem(idProduct);
            });
    }
}
