package pb.estoque.catalog.shared.kernel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record MonetaryValue(BigDecimal value) {
    public MonetaryValue {
        Objects.requireNonNull(value,"O value é obrigatorio!");
        value = value.setScale(2, RoundingMode.HALF_UP);
        if(value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O value deve ser maior que zero!");
        }
    }
    public static MonetaryValue of (BigDecimal value){
        return new MonetaryValue(value);
    }
    public static MonetaryValue of (String value){
        return new MonetaryValue(new BigDecimal(value));
    }
}