package jp.co.unirita.webapp.sample.form.expense;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExpenseForm {
    private int year;
    private int month;
    private int day;
    private List<ExpenseFormRow> expenseFormRowList;
}
