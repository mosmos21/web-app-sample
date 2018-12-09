package jp.co.unirita.webapp.sample.form.expense;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class ExpensePanelRow {
    private Date date;
    private String category;
    private int price;

    public ExpensePanelRow(Date date, int price) {
        this.date = date;
        this.price = price;
    }
}
