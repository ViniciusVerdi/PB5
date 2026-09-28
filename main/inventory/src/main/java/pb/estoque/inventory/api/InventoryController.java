package pb.estoque.inventory.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.estoque.inventory.api.dtos.requests.StockInboundDTO;
import pb.estoque.inventory.api.dtos.requests.StockOutboundDTO;
import pb.estoque.inventory.api.dtos.responses.InventoryResponseDTO;
import pb.estoque.inventory.api.mappers.InventoryMapper;
import pb.estoque.inventory.application.command.GetStockCommand;
import pb.estoque.inventory.application.command.ProcessInboundCommand;
import pb.estoque.inventory.application.command.ProcessOutboundCommand;
import pb.estoque.inventory.application.port.in.GetAllStockPortIn;
import pb.estoque.inventory.application.port.in.GetStockPortIn;
import pb.estoque.inventory.application.port.in.ProcessInboundPortIn;
import pb.estoque.inventory.application.port.in.ProcessOutboundPortIn;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/estoque")
public class InventoryController {

    private final GetStockPortIn getStock;
    private final GetAllStockPortIn getAllStock;
    private final ProcessInboundPortIn processInbound;
    private final ProcessOutboundPortIn processOutbound;
    private final InventoryMapper inventoryMapper;

    public InventoryController(GetStockPortIn getStock,
                               GetAllStockPortIn getAllStock,
                               ProcessInboundPortIn processInbound,
                               ProcessOutboundPortIn processOutbound,
                               InventoryMapper inventoryMapper) {
        this.getStock = getStock;
        this.getAllStock = getAllStock;
        this.processInbound = processInbound;
        this.processOutbound = processOutbound;
        this.inventoryMapper = inventoryMapper;
    }

    @GetMapping("/id")
    public ResponseEntity<InventoryResponseDTO> getStock(@RequestParam UUID id) {
        InventoryResponseDTO response = inventoryMapper.toDTO(getStock.execute(new GetStockCommand(id)));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponseDTO>> getAll() {
        List<InventoryResponseDTO> response = inventoryMapper.toListDTO(getAllStock.execute());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/entrada")
    public ResponseEntity<Void> inboundStock(@RequestBody StockInboundDTO dto) {
        processInbound.execute(new ProcessInboundCommand(dto.getIdProduct(), dto.getAmount()));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/saida")
    public ResponseEntity<Void> outboundStock(@RequestBody StockOutboundDTO dto) {
        processOutbound.execute(new ProcessOutboundCommand(dto.getIdProduct(), dto.getAmount()));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}