package pb.estoque.history.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.estoque.history.dtos.requests.MovementRequestDTO;
import pb.estoque.history.entities.MovementType;
import pb.estoque.history.services.MovementHistoryService;

@RestController
@RequestMapping("/historico")
public class HistoryController {

    private final MovementHistoryService movementHistoryService;

    public HistoryController(MovementHistoryService movementHistoryService) {
        this.movementHistoryService = movementHistoryService;
    }

    @PostMapping("/entrada")
    public ResponseEntity<Void> addMove(@RequestBody MovementRequestDTO dto) {
        movementHistoryService.recordMovement(
            dto.getIdProduct(),
            MovementType.INBOUND,
            dto.getQuantity()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/saida")
    public ResponseEntity<Void> withdrawMove(
        @RequestBody MovementRequestDTO dto
    ) {
        movementHistoryService.recordMovement(
            dto.getIdProduct(),
            MovementType.OUTBOUND,
            dto.getQuantity()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
