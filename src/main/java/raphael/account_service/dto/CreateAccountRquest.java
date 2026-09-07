package raphael.account_service.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateAccountRquest(
        @NotNull String owner,
        @NotNull @Positive BigDecimal balance) {

}
