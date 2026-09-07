package raphael.account_service.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Transfers {

    private Accounts fromAccount;
    private Accounts toAccount;
    private BigDecimal amount;
    private String status;

}
