package raphael.account_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import raphael.account_service.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
