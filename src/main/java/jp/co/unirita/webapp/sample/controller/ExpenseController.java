package jp.co.unirita.webapp.sample.controller;

import jp.co.unirita.webapp.sample.constant.ModelKey;
import jp.co.unirita.webapp.sample.domain.account.Account;
import jp.co.unirita.webapp.sample.domain.master.category.CategoryMaster;
import jp.co.unirita.webapp.sample.form.expense.ExpenseForm;
import jp.co.unirita.webapp.sample.form.expense.ExpensePanelRow;
import jp.co.unirita.webapp.sample.service.CategoryService;
import jp.co.unirita.webapp.sample.service.ExpenseService;
import jp.co.unirita.webapp.sample.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ExpenseService expenseService;

    @GetMapping
    public String getExpenseList(
            Model model,
            @RequestParam(value = "year", required = false) Optional<Integer> year,
            @RequestParam(value = "month", required = false) Optional<Integer> month,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(value = "category", required = false) Optional<Long> optCategoryId,
            @AuthenticationPrincipal Account account) {
        if (year.isPresent() && month.isPresent()) {
            int y = DateUtil.parseYear(year);
            int m = DateUtil.parseMonth(month);
            java.sql.Date from = DateUtil.createDate(y, m);
            java.sql.Date to = DateUtil.add(DateUtil.createDate(y, m + 1), Calendar.DATE, -1);
            return String.format("redirect:/expense?startDate=%s&endDate=%s", DateUtil.toString(from), DateUtil.toString(to));

        }
        long categoryId = optCategoryId.orElse(-1L);
        List<CategoryMaster> categoryList = categoryService.getAllCategory();
        List<ExpensePanelRow> expenseList = expenseService.searchExpense(
                account.getId(), DateUtil.parseDate(startDate), DateUtil.parseDate(endDate), categoryId);
        log.info("getExpenseList accountId: {}, startDate: {}, endDate: {}, category: {}, result: {}",
                account.getId(), startDate, endDate, categoryId, expenseList.size());
        model.addAttribute(ModelKey.CATEGORY_LIST, categoryList);
        model.addAttribute(ModelKey.EXPENSE_LIST, expenseList);

        return "expense-list";
    }

    @GetMapping("/new")
    public String expenseForm(Model model) {
        model.addAttribute(ModelKey.CATEGORY_LIST, categoryService.getAllCategory());
        return "expense-form";
    }

    @PostMapping("/new")
    public String addExpense(ExpenseForm form, @AuthenticationPrincipal Account account) {
        expenseService.registerExpense(
                account.getId(),
                DateUtil.createDate(form.getYear(), form.getMonth(), form.getDay()),
                form.getExpenseFormRowList());
        return String.format("redirect:/?year=%d&month=%d", form.getYear(), form.getMonth());
    }
}
