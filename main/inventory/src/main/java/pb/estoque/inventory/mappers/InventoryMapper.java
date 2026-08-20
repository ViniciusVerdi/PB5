package pb.estoque.inventory.mappers;

import org.mapstruct.Mapper;
import pb.estoque.inventory.dtos.responses.InventoryResponseDTO;
import pb.estoque.inventory.entities.InventoryItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    InventoryResponseDTO toDTO(InventoryItem item);

    List<InventoryResponseDTO> toListDTO(List<InventoryItem> items);
}