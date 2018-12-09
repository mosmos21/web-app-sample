package jp.co.unirita.webapp.sample.controller;

import jp.co.unirita.webapp.sample.constant.ModelKey;
import jp.co.unirita.webapp.sample.domain.account.Account;
import jp.co.unirita.webapp.sample.domain.master.category.CategoryMaster;
import jp.co.unirita.webapp.sample.form.chart.BudgetChart;
import jp.co.unirita.webapp.sample.form.chart.ExpenseChart;
import jp.co.unirita.webapp.sample.service.BudgetService;
import jp.co.unirita.webapp.sample.service.CategoryService;
import jp.co.unirita.webapp.sample.service.ExpenseService;
import jp.co.unirita.webapp.sample.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    BudgetService budgetService;
    @Autowired
    ExpenseService expenseService;

    @GetMapping
    public String index(
            Model model,
            @RequestParam(value = "year", required = false) Optional<Integer> optionalYear,
            @RequestParam(value = "month", required = false) Optional<Integer> optionalMonth,
            @AuthenticationPrincipal Account account) {
        int year = DateUtil.parseYear(optionalYear);
        int month = DateUtil.parseMonth(optionalMonth);
        // タイトル
        model.addAttribute(ModelKey.YEAR, year);
        model.addAttribute(ModelKey.MONTH, month);

        // 予算パネル
        model.addAttribute(ModelKey.BUDGET_LIST,
                budgetService.getBudgetFormRowList(account.getId(), year, month));
        // 実績パネル
        model.addAttribute(ModelKey.EXPENSE_LIST,
                expenseService.getExpenseFormRow(account.getId(), year, month, 4));

        // グラフ
        List<CategoryMaster> categoryMasters = categoryService.getAllCategory();
        BudgetChart budgetChart = new BudgetChart(categoryMasters,
                budgetService.getBudget(account.getId(), year, month));
        ExpenseChart expenseChart = new ExpenseChart(categoryMasters,
                expenseService.getExpenseSummaryOfCategory(account.getId(), year, month));
        model.addAttribute(ModelKey.BUDGET_CHART, budgetChart);
        model.addAttribute(ModelKey.EXPENSE_CHART, expenseChart);

        log.info("[index()] accountId: {}, year: {}, month: {}",account.getId(), year, month);
        return "index";
    }
}
