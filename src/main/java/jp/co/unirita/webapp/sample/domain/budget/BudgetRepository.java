package jp.co.unirita.webapp.sample.domain.budget;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Budget.PK> {
    List<Budget> findByAccountIdAndDate(long accountId, Date date);
}
