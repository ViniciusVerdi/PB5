package pb.estoque.inventory.insfrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pb.estoque.inventory.application.command.CreateInventoryItemCommand;
import pb.estoque.inventory.application.port.in.CreateInventoryItemPortIn;

@Component
public class ProductCreatedListener {
    private static final Logger log = LoggerFactory.getLogger(ProductCreatedListener.class);
    private static final String PRODUCT_CREATED_TOPIC = "catalog.product.created";

    private final CreateInventoryItemPortIn createInventoryItem;
    private final ObjectMapper objectMapper;

    public ProductCreatedListener(CreateInventoryItemPortIn createInventoryItem, ObjectMapper objectMapper) {
        this.createInventoryItem = createInventoryItem;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = PRODUCT_CREATED_TOPIC, groupId = "inventory-product-created")
    public void onProductCreated(String payload) {
        try {
            ProductCreatedMessage message = objectMapper.readValue(payload, ProductCreatedMessage.class);
            log.info("Consuming ProductCreated for product {}", message.id());
            createInventoryItem.execute(new CreateInventoryItemCommand(message.id()));
        } catch (Exception e) {
            log.error("Failed to process ProductCreated event", e);
        }
    }
}

