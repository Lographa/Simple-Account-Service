package raphael.account_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import raphael.account_service.entity.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
