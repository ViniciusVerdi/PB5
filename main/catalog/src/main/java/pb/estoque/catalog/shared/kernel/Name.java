package pb.estoque.catalog.shared.kernel;

public record Name (String value){
    public Name {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("O conteúdo é obrigatório!");
        }
        value = value.trim();
    }
}
