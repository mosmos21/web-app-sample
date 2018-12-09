package jp.co.unirita.webapp.sample.domain.expense;

import jp.co.unirita.webapp.sample.domain.tag.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Data
@Entity
@ToString
@NoArgsConstructor
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_id")
    private long accountId;

    @Column(name = "category_master_id")
    private long categoryMasterId;

    @Column(name = "date")
    private Date date;

    @Column(name = "content")
    private String content;

    @Column(name = "price")
    private int price;

    @Column(name = "insert_time")
    private Timestamp insertTime;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "expense_id")
    private List<Tag> tagList;

    public Expense(long accountId, long categoryMasterId, Date date, String content, int price) {
        this.accountId = accountId;
        this.categoryMasterId = categoryMasterId;
        this.date = date;
        this.content = content;
        this.price = price;
        this.insertTime = new Timestamp(System.currentTimeMillis());
    }
}
