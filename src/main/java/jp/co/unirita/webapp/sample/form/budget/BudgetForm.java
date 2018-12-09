package jp.co.unirita.webapp.sample.form.budget;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BudgetForm {
    private int year;
    private int month;
    private List<BudgetFormRow> budgetFormRowList;
}
