package pb.estoque.inventory.api.mappers;

import org.mapstruct.Mapper;
import pb.estoque.inventory.api.dtos.responses.InventoryResponseDTO;
import pb.estoque.inventory.domain.model.InventoryItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    InventoryResponseDTO toDTO(InventoryItem item);

    List<InventoryResponseDTO> toListDTO(List<InventoryItem> items);
}