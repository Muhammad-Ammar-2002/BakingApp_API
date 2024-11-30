package net.javaguides.banking.Repos;

import net.javaguides.banking.Entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository< AccountEntity,Long> {
}
