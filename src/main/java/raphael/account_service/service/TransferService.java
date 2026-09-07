package raphael.account_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import raphael.account_service.dto.Accounts;
import raphael.account_service.dto.CreateAccountRquest;
import raphael.account_service.dto.CreateTransferRequest;
import raphael.account_service.dto.Transfers;
import raphael.account_service.entity.Account;
import raphael.account_service.entity.Transfer;
import raphael.account_service.enums.TransferStatus;
import raphael.account_service.exception.InsufficientBalanceException;
import raphael.account_service.mapper.AccountMapper;
import raphael.account_service.mapper.TransferMapper;
import raphael.account_service.repository.AccountRepository;
import raphael.account_service.repository.TransferRepository;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;
    private final TransferMapper transferMapper;
    private final AccountMapper accountMapper;

    public TransferService(TransferRepository transferRepository, AccountRepository accountRepository,
            TransferMapper transferMapper, AccountMapper accountMapper) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
        this.transferMapper = transferMapper;
        this.accountMapper = accountMapper;
    }

    public List<Transfers> findAllTransfers() {
        return transferRepository.findAll().stream()
                .map(transferMapper::toDto)
                .toList();
    }

    @Transactional
    public Transfers createTransfer(CreateTransferRequest request) {
        if (request.fromAccountId().equals(request.toAccountId())) {
            throw new IllegalArgumentException("Origem e destino devem ser contas diferentes");
        }
        Account from = accountRepository.findById(request.fromAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Conta de origem não encontrada"));
        Account to = accountRepository.findById(request.toAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Conta de destino não encontrada"));
        if (from.getBalance().compareTo(request.amount()) < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente na conta de origem");
        }
        try {
            from.setBalance(from.getBalance().subtract(request.amount()));
            to.setBalance(to.getBalance().add(request.amount()));
            Transfer transfer = new Transfer();
            transfer.setFromAccount(from);
            transfer.setToAccount(to);
            transfer.setAmount(request.amount());
            transfer.setStatus(TransferStatus.COMPLETED);
            Transfer saved = transferRepository.save(transfer);
            return transferMapper.toDto(saved);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao criar transferência");
        }

    }

    @Transactional
    public Accounts createAccount(CreateAccountRquest request) {
        Account account = new Account();
        account.setOwner(request.owner());
        account.setBalance(request.balance());
        Account savedAccount = accountRepository.save(account);

        return accountMapper.toDto(savedAccount);
    }

    @Transactional
    public String deleteAccount(Long id) {
        try {
            accountRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Conta não foi encontrada"));
            accountRepository.deleteById(id);
            return "Conta deletada com sucesso";
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao deletar conta");
        }

    }
}
