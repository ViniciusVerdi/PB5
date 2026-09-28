# Sistema de Estoque e Catálogo

Sistema de microsserviços para gestão de catálogo (produtos/categorias), estoque e histórico de movimentações, com arquitetura orientada a eventos.

## Arquitetura

DDD + hexagonal + orientada a eventos. Cada microsserviço segue a mesma estrutura (`api`, `application`, `domain`, `infrastructure`, `shared/kernel`), com domínio rico (aggregates e value objects) e comunicação assíncrona via Kafka.

## Bounded Contexts

| Serviço         | Responsabilidade                           | Porta |
| --------------- | ------------------------------------------ | ----- |
| `catalog`       | Catálogo de produtos e categorias          | 8081  |
| `inventory`     | Estoque dos produtos                       | 8082  |
| `history`       | Histórico de movimentações (entrada/saída) | 8083  |
| `apigateway`    | Entrada única (roteamento via Eureka)      | 8080  |
| `eureka-server` | Service discovery                          | 8761  |

## Fluxo de eventos (Kafka)

- `ProductCreated` (`catalog.product.created`) — catalog → inventory (cria estoque inicial, qtd 0).
- `StockInbound` (`inventory.stock.inbound`) — inventory → history (registra entrada).
- `StockOutbound` (`inventory.stock.outbound`) — inventory → history (registra saída).

## Como rodar

```bash
docker compose up --build -d
```

## Como testar

```bash
mvn verify
```

Fluxo via gateway (`http://localhost:8080`): produtos em `/catalog-ms/**`, estoque em `/inventory-ms/estoque/**`, histórico em `/history-ms/historico`.

## Observabilidade

- Rastreamento distribuído: Zipkin (`http://localhost:9411`).
- Agregação de logs: Grafana (`http://localhost:3000`), via Loki + Promtail.

## CI/CD

GitHub Actions: CI (build + testes via `mvn verify`) e CD (build e push das imagens Docker para o GHCR).
