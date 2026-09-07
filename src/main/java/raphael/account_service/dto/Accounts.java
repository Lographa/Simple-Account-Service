package raphael.account_service.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Accounts {

    private final Long id;
    private String owner;
    private BigDecimal balance;

    public Accounts(Long id, String owner, BigDecimal balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
    }
}
