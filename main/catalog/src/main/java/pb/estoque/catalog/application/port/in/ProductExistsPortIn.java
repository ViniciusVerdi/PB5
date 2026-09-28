package pb.estoque.catalog.application.port.in;

import java.util.UUID;

public interface ProductExistsPortIn {
    boolean exists(UUID id);
}
