package jp.co.unirita.webapp.sample.service;

import jp.co.unirita.webapp.sample.domain.account.AccountRepository;
import jp.co.unirita.webapp.sample.domain.budget.Budget;
import jp.co.unirita.webapp.sample.domain.budget.BudgetRepository;
import jp.co.unirita.webapp.sample.domain.master.category.CategoryMaster;
import jp.co.unirita.webapp.sample.domain.master.category.CategoryMasterRepository;
import jp.co.unirita.webapp.sample.form.budget.BudgetFormRow;
import jp.co.unirita.webapp.sample.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    CategoryMasterRepository categoryMasterRepository;

    public List<Budget> getBudget(long accountId, int year, int month) {
        Date date = DateUtil.createDate(year, month);
        return budgetRepository.findByAccountIdAndDate(accountId, date);
    }

    public List<BudgetFormRow> getBudgetFormRowList(long accountId, int year, int month) {
        List<Budget> budgetList = getBudget(accountId, year, month);
        List<CategoryMaster> categoryMasterList = categoryMasterRepository.findAll();
        Map<Long, String> categoryMap = new HashMap<>();
        categoryMasterList.forEach(c -> categoryMap.put(c.getId(), c.getName()));
        return budgetList.stream()
                .map(b -> new BudgetFormRow(b.getCategoryMasterId(), categoryMap.get(b.getCategoryMasterId()), b.getPrice()))
                .collect(Collectors.toList());
    }

    public void registerBudget(long accountId, int year, int month, List<BudgetFormRow> row) {
        Date date = DateUtil.createDate(year, month);
        budgetRepository.saveAll(row.stream()
                .map(r -> new Budget(accountId, r.getId(), date, r.getPrice()))
                .collect(Collectors.toList()));
    }
}
