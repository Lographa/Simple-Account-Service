package raphael.account_service.mapper;

import raphael.account_service.dto.Accounts;
import raphael.account_service.entity.Account;

public class AccountMapper {

    public Accounts toDto(Account account) {
        if (account == null) {
            return null;
        }
        return new Accounts(account.getId(), account.getOwner(), account.getBalance());
    }

}
