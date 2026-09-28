package pb.estoque.catalog.application.port.in;

import pb.estoque.catalog.domain.model.Product;

import java.util.List;

public interface GetAllProductsPortIn {
    List<Product> getAll();
}
