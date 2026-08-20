package pb.estoque.inventory.integration.implemantation;

import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import pb.estoque.inventory.integration.CatalogIntegrationService;

@Component
public class CatalogIntegrationAdapter implements CatalogIntegrationService {

    private final RestClient restClient;

    public CatalogIntegrationAdapter(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
            .baseUrl("http://catalog-ms")
            .build();
    }

    @Override
    public boolean productExists(UUID idProduct) {
        return send("/produtos/id", idProduct);
    }

    private boolean send(String path, UUID idProduct) {
        Boolean exists = restClient
            .get()
            .uri(path + "?id=" + idProduct)
            .retrieve()
            .body(Boolean.class);
        return Boolean.TRUE.equals(exists);
    }
}
