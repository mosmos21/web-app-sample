package jp.co.unirita.webapp.sample.controller;

import jp.co.unirita.webapp.sample.constant.ModelKey;
import jp.co.unirita.webapp.sample.domain.account.Account;
import jp.co.unirita.webapp.sample.form.budget.BudgetForm;
import jp.co.unirita.webapp.sample.service.BudgetService;
import jp.co.unirita.webapp.sample.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    BudgetService budgetService;

    @GetMapping("/new")
    public String budgetForm(Model model) {
        model.addAttribute(ModelKey.CATEGORY_LIST, categoryService.getAllCategory());
        return "budget-form";
    }

    @PostMapping("/new")
    public String addBudget(BudgetForm budgetForm, @AuthenticationPrincipal Account account) {
        budgetService.registerBudget(
                account.getId(), budgetForm.getYear(), budgetForm.getMonth(), budgetForm.getBudgetFormRowList());
        return String.format("redirect:/?year=%d&month=%d", budgetForm.getYear(), budgetForm.getMonth());
    }
}
