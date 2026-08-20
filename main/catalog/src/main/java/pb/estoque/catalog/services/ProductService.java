package pb.estoque.catalog.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pb.estoque.catalog.dtos.requests.ProductRequestDTO;
import pb.estoque.catalog.dtos.responses.ProductResponseDTO;
import pb.estoque.catalog.entities.Category;
import pb.estoque.catalog.entities.Product;
import pb.estoque.catalog.mappers.ProductMapper;
import pb.estoque.catalog.repositories.CategoryRepository;
import pb.estoque.catalog.repositories.ProductRepository;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return productMapper.toDTO(products);
    }

    @Transactional(readOnly = true)
    public boolean existsById(UUID productId) {
        return productRepository.existsById(productId);
    }

    @Transactional
    public ProductResponseDTO addProduct(ProductRequestDTO dto) {
        validateUniqueProduct(dto.getName(), dto.getIdCategory());

        Category category = getCategoryOrThrow(dto.getIdCategory());

        Product product = new Product(dto.getName(), dto.getPrice(), category);
        Product newProduct = productRepository.save(product);

        return productMapper.toDTO(newProduct);
    }
    private void validateUniqueProduct(String name, UUID idCategory) {
        if (productRepository.existsByNameAndCategoryId(name, idCategory)) {
            throw new IllegalStateException("Erro: Produto já cadastrado!");
        }
    }

    private Category getCategoryOrThrow(UUID idCategory) {
        return categoryRepository.findById(idCategory)
                .orElseThrow(() -> new IllegalArgumentException("Erro: Categoria não encontrada!"));
    }
}
