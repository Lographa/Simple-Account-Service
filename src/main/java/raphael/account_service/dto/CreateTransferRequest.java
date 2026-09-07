package raphael.account_service.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateTransferRequest(
                @NotNull Long fromAccountId,
                @NotNull Long toAccountId,
                @NotNull @Positive BigDecimal amount) {
}