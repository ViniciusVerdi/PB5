package pb.estoque.inventory.insfrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pb.estoque.inventory.application.port.out.DomainEventPublisherPort;
import pb.estoque.inventory.domain.event.InventoryItemAddedQuantity;
import pb.estoque.inventory.domain.event.InventoryItemSubtractedQuantity;
import pb.estoque.inventory.shared.kernel.DomainEvent;

@Component
public class KafkaDomainEventPublisherAdapter implements DomainEventPublisherPort {
    private static final Logger log = LoggerFactory.getLogger(KafkaDomainEventPublisherAdapter.class);
    private static final String STOCK_INBOUND_TOPIC = "inventory.stock.inbound";
    private static final String STOCK_OUTBOUND_TOPIC = "inventory.stock.outbound";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaDomainEventPublisherAdapter(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(DomainEvent event) {
        if (event instanceof InventoryItemAddedQuantity e) {
            publishStockInbound(e);
        } else if (event instanceof InventoryItemSubtractedQuantity e) {
            publishStockOutbound(e);
        }
    }

    private void publishStockInbound(InventoryItemAddedQuantity event) {
        try {
            StockInboundMessage message = new StockInboundMessage(
                    event.id(), event.idProduct(), event.amount(), event.timestamp()
            );
            send(STOCK_INBOUND_TOPIC, event.idProduct().toString(), message);
        } catch (Exception e) {
            log.error("Failed to publish StockInbound event", e);
        }
    }

    private void publishStockOutbound(InventoryItemSubtractedQuantity event) {
        try {
            StockOutboundMessage message = new StockOutboundMessage(
                    event.id(), event.idProduct(), event.amount(), event.timestamp()
            );
            send(STOCK_OUTBOUND_TOPIC, event.idProduct().toString(), message);
        } catch (Exception e) {
            log.error("Failed to publish StockOutbound event", e);
        }
    }

    private void send(String topic, String key, Object message) throws Exception {
        String payload = objectMapper.writeValueAsString(message);
        kafkaTemplate.send(topic, key, payload);
        log.info("Published {} to topic {}", message.getClass().getSimpleName(), topic);
    }
}
