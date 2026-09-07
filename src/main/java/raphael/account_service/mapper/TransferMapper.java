package raphael.account_service.mapper;

import org.springframework.stereotype.Component;

import raphael.account_service.dto.Accounts;
import raphael.account_service.dto.Transfers;
import raphael.account_service.entity.Transfer;

@Component
public class TransferMapper {

    public Transfers toDto(Transfer transfer) {
        Transfers dto = new Transfers();
        dto.setFromAccount(toAccountDto(transfer.getFromAccount()));
        dto.setToAccount(toAccountDto(transfer.getToAccount()));
        dto.setAmount(transfer.getAmount());
        dto.setStatus(transfer.getStatus() != null ? transfer.getStatus().name() : null);
        return dto;
    }

    private Accounts toAccountDto(raphael.account_service.entity.Account account) {
        if (account == null) {
            return null;
        }
        return new Accounts(account.getId(), account.getOwner(), account.getBalance());
    }
}
