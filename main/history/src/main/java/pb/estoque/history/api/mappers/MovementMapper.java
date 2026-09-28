package pb.estoque.history.api.mappers;

import org.mapstruct.Mapper;
import pb.estoque.history.api.dtos.responses.MovementResponseDTO;
import pb.estoque.history.domain.model.MovementRecord;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementMapper {
    MovementResponseDTO toDTO(MovementRecord record);

    List<MovementResponseDTO> toListDTO(List<MovementRecord> records);
}
