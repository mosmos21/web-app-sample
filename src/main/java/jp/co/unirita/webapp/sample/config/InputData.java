package jp.co.unirita.webapp.sample.config;

import jp.co.unirita.webapp.sample.domain.account.Account;
import jp.co.unirita.webapp.sample.domain.account.AccountRepository;
import jp.co.unirita.webapp.sample.domain.budget.Budget;
import jp.co.unirita.webapp.sample.domain.budget.BudgetRepository;
import jp.co.unirita.webapp.sample.domain.expense.Expense;
import jp.co.unirita.webapp.sample.domain.expense.ExpenseRepository;
import jp.co.unirita.webapp.sample.domain.master.category.CategoryMaster;
import jp.co.unirita.webapp.sample.domain.master.category.CategoryMasterRepository;
import jp.co.unirita.webapp.sample.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class InputData {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    CategoryMasterRepository categoryMasterRepository;

    @PostConstruct
    public void insert() {
        accountRepository.saveAll(Arrays.asList(
                new Account("testUser1", passwordEncoder.encode("password")),
                new Account("testUser2", passwordEncoder.encode("password"))
        ));

        categoryMasterRepository.saveAll(Arrays.asList(
                new CategoryMaster("食費"),
                new CategoryMaster("生活費"),
                new CategoryMaster("雑費")
        ));

        Date date = DateUtil.createDate(2018, 12);
        budgetRepository.saveAll(Arrays.asList(
                new Budget(1L, 1L, date, 12345),
                new Budget(1L, 2L, date, 34569),
                new Budget(1L, 3L, date, 65156)
        ));

        List<Expense> expenseList = Arrays.asList(
                new Expense(1, 1, DateUtil.createDate(2018, 12, 1), "", 123),
                new Expense(1, 2, DateUtil.createDate(2018, 12, 1), "", 567),
                new Expense(1, 3, DateUtil.createDate(2018, 12, 1), "", 1000),
                new Expense(1, 1, DateUtil.createDate(2018, 12, 2), "", 456),
                new Expense(1, 2, DateUtil.createDate(2018, 12, 2), "", 678),
                new Expense(1, 3, DateUtil.createDate(2018, 12, 3), "", 673));
        for(Expense expense : expenseList) {
            expense.setInsertTime(new Timestamp(System.currentTimeMillis() + (long)(Math.random() * 10000)));
        }
        expenseRepository.saveAll(expenseList);
    }
}
