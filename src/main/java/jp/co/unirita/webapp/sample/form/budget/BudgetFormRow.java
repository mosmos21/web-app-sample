package jp.co.unirita.webapp.sample.form.budget;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetFormRow {
    private long id;
    private String name;
    private int price;
}