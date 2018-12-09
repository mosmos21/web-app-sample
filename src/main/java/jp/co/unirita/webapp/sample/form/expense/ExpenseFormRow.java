package jp.co.unirita.webapp.sample.form.expense;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class ExpenseFormRow {
    private Date date;
    private long categoryId;
    private String categoryName;
    private int price;
    private int content;

    public ExpenseFormRow(Date date, String categoryName, int price) {
        this.date = date;
        this.categoryName = categoryName;
        this.price = price;
    }
}
