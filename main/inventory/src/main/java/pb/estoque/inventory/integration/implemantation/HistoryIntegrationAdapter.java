package pb.estoque.inventory.integration.implemantation;

import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import pb.estoque.inventory.integration.HistoryIntegrationService;

@Component
public class HistoryIntegrationAdapter implements HistoryIntegrationService {

    private final RestClient restClient;

    public HistoryIntegrationAdapter(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
            .baseUrl("http://history-ms")
            .build();
    }

    @Override
    public void recordInbound(UUID idProduct, Integer quantity) {
        send("/historico/entrada", idProduct, quantity);
    }

    @Override
    public void recordOutbound(UUID idProduct, Integer quantity) {
        send("/historico/saida", idProduct, quantity);
    }

    private void send(String path, UUID idProduct, Integer quantity) {
        restClient
            .post()
            .uri(path)
            .body(new MovementRequest(idProduct, quantity))
            .retrieve()
            .toBodilessEntity();
    }

    private record MovementRequest(UUID idProduct, Integer quantity) {}
}
