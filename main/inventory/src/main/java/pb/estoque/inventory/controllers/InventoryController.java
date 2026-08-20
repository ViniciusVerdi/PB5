package pb.estoque.inventory.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.estoque.inventory.dtos.requests.StockInboundDTO;
import pb.estoque.inventory.dtos.requests.StockOutboundDTO;
import pb.estoque.inventory.dtos.responses.InventoryResponseDTO;
import pb.estoque.inventory.services.InventoryService;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/estoque")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/id")
    public ResponseEntity<InventoryResponseDTO> getStock(@RequestParam UUID id) {
        InventoryResponseDTO response = inventoryService.getStock(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping()
    public ResponseEntity<List<InventoryResponseDTO>> getStock() {
        List<InventoryResponseDTO> response = inventoryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/entrada")
    public ResponseEntity<Void> inboundStock(@RequestBody StockInboundDTO dto) {
        inventoryService.processInbound(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/saida")
    public ResponseEntity<Void> outboundStock(@RequestBody StockOutboundDTO dto) {
        inventoryService.processOutbound(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
