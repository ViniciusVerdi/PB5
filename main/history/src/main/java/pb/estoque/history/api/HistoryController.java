package pb.estoque.history.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pb.estoque.history.api.dtos.responses.MovementResponseDTO;
import pb.estoque.history.api.mappers.MovementMapper;
import pb.estoque.history.application.port.in.GetMovementsPortIn;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/historico")
public class HistoryController {

    private final GetMovementsPortIn getMovements;
    private final MovementMapper movementMapper;

    public HistoryController(GetMovementsPortIn getMovements, MovementMapper movementMapper) {
        this.getMovements = getMovements;
        this.movementMapper = movementMapper;
    }

    @GetMapping
    public ResponseEntity<List<MovementResponseDTO>> getAll() {
        List<MovementResponseDTO> response = movementMapper.toListDTO(getMovements.execute());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
