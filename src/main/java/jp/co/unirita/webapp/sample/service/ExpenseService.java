package jp.co.unirita.webapp.sample.service;

import jp.co.unirita.webapp.sample.constant.Constant;
import jp.co.unirita.webapp.sample.domain.expense.Expense;
import jp.co.unirita.webapp.sample.domain.expense.ExpenseRepository;
import jp.co.unirita.webapp.sample.form.expense.ExpenseFormRow;
import jp.co.unirita.webapp.sample.form.expense.ExpensePanelRow;
import jp.co.unirita.webapp.sample.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    CategoryService categoryService;
    @Autowired
    ExpenseRepository expenseRepository;

    public List<ExpenseFormRow> getExpenseFormRow(long accountId, int year, int month, int count) {
        List<Expense> expenseList = getExpense(accountId, year, month);
        Map<Long, String> categoryMap = categoryService.getCategoryMap();
        expenseList.sort(Comparator.comparing(Expense::getInsertTime).reversed());
        return expenseList.subList(0, Math.min(expenseList.size(), 4))
                .stream()
                .map(e -> new ExpenseFormRow(e.getDate(), categoryMap.get(e.getCategoryMasterId()), e.getPrice()))
                .collect(Collectors.toList());
    }

    public List<Expense> getExpenseSummaryOfCategory(long accountId, int year, int month) {
        List<Expense> expenseList = getExpense(accountId, year, month);
        Map<Long, List<Expense>> expenseMap = expenseList.stream()
                .collect(Collectors.groupingBy(Expense::getCategoryMasterId));
        return expenseMap.entrySet().stream()
                .map(e -> new Expense(accountId, e.getKey(), null, "",
                        e.getValue().stream().mapToInt(Expense::getPrice).sum()))
                .collect(Collectors.toList());
    }

    public List<ExpensePanelRow> searchExpense(long accountId, Optional<Date> startDate, Optional<Date> endDate, long categoryId) {
        List<Expense> expenseList = expenseRepository
                .findByAccountIdAndDateBetween(accountId, startDate.orElse(DateUtil.MIN_DATE), endDate.orElse(DateUtil.MAX_DATE))
                .stream().filter(e -> categoryId == Constant.CATEGORY_NONE || e.getCategoryMasterId() == categoryId)
                .collect(Collectors.toList());
        Map<Long, String> categoryMap = categoryService.getCategoryMap();
        return expenseList.stream()
                .map(e -> new ExpensePanelRow(e.getDate(), categoryMap.get(e.getCategoryMasterId()), e.getPrice()))
                .collect(Collectors.toList());
    }

    public void registerExpense(long accountId, Date date, List<ExpenseFormRow> rowList) {
        expenseRepository.saveAll(rowList.stream()
                .map(r -> new Expense(accountId, r.getCategoryId(), date, "", r.getPrice()))
                .collect(Collectors.toList()));
    }

    private List<Expense> getExpense(long accountId, int year, int month) {
        Date from = DateUtil.createDate(year, month, 1);
        Date to = DateUtil.add(DateUtil.createDate(year, month + 1, 1), Calendar.DATE, -1);
        return expenseRepository.findByAccountIdAndDateBetween(accountId, from, to);
    }
}
