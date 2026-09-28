package pb.estoque.history.infrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pb.estoque.history.application.command.RecordMovementCommand;
import pb.estoque.history.application.port.in.RecordMovementPortIn;
import pb.estoque.history.domain.model.MovementType;

@Component
public class StockMovementListener {

    private static final Logger log = LoggerFactory.getLogger(StockMovementListener.class);
    private static final String STOCK_INBOUND_TOPIC = "inventory.stock.inbound";
    private static final String STOCK_OUTBOUND_TOPIC = "inventory.stock.outbound";

    private final RecordMovementPortIn recordMovement;
    private final ObjectMapper objectMapper;

    public StockMovementListener(RecordMovementPortIn recordMovement, ObjectMapper objectMapper) {
        this.recordMovement = recordMovement;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = STOCK_INBOUND_TOPIC, groupId = "history-ms")
    public void onStockInbound(String payload) {
        try {
            StockInboundMessage message = objectMapper.readValue(payload, StockInboundMessage.class);
            log.info("Consuming StockInbound for product {}", message.idProduct());
            recordMovement.execute(new RecordMovementCommand(message.idProduct(), MovementType.INBOUND, message.amount()));
        } catch (Exception e) {
            log.error("Failed to process StockInbound event", e);
        }
    }

    @KafkaListener(topics = STOCK_OUTBOUND_TOPIC, groupId = "history-ms")
    public void onStockOutbound(String payload) {
        try {
            StockOutboundMessage message = objectMapper.readValue(payload, StockOutboundMessage.class);
            log.info("Consuming StockOutbound for product {}", message.idProduct());
            recordMovement.execute(new RecordMovementCommand(message.idProduct(), MovementType.OUTBOUND, message.amount()));
        } catch (Exception e) {
            log.error("Failed to process StockOutbound event", e);
        }
    }
}
