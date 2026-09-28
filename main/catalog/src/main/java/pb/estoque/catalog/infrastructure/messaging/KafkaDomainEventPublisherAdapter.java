package pb.estoque.catalog.infrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pb.estoque.catalog.application.port.out.DomainEventPublisher;
import pb.estoque.catalog.domain.event.CategoryCreated;
import pb.estoque.catalog.domain.event.ProductCreated;
import pb.estoque.catalog.shared.kernel.DomainEvent;

@Component
public class KafkaDomainEventPublisherAdapter implements DomainEventPublisher {
    private static final Logger log = LoggerFactory.getLogger(KafkaDomainEventPublisherAdapter.class);
    private static final String CATEGORY_CREATED_TOPIC = "catalog.category.created";
    private static final String PRODUCT_CREATED_TOPIC = "catalog.product.created";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaDomainEventPublisherAdapter(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }


    @Override
    public void publish(DomainEvent event) {
        if (event instanceof CategoryCreated created) {
            publishCategoryCreated(created);
        } else if (event instanceof ProductCreated created) {
            publishProductCreated(created);
        }
    }

    private void publishCategoryCreated(CategoryCreated event) {
        try {
            CategoryCreatedMessage message = new CategoryCreatedMessage(
                    event.id(),
                    event.name().value(),
                    event.description(),
                    event.timestamp()
            );
            send(CATEGORY_CREATED_TOPIC, event.id().toString(), message);
        } catch (Exception e) {
            log.error("Failed to publish CategoryCreated event", e);
        }
    }

    private void publishProductCreated(ProductCreated event) {
        try {
            ProductCreatedMessage message = new ProductCreatedMessage(
                    event.id(),
                    event.name().value(),
                    event.price().value(),
                    event.idCategory(),
                    event.timestamp()
            );
            send(PRODUCT_CREATED_TOPIC, event.id().toString(), message);
        } catch (Exception e) {
            log.error("Failed to publish ProductCreated event", e);
        }
    }

    private void send(String topic, String key, Object message) throws Exception {
        String payload = objectMapper.writeValueAsString(message);
        kafkaTemplate.send(topic, key, payload);
        log.info("Published {} to topic {}", message.getClass().getSimpleName(), topic);
    }
}

