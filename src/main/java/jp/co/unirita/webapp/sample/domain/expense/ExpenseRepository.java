package jp.co.unirita.webapp.sample.domain.expense;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByAccountIdAndDateBetween(long accountId, Date from, Date to);
}
