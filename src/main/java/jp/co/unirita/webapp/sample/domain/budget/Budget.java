package jp.co.unirita.webapp.sample.domain.budget;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@ToString
@NoArgsConstructor
@Table(name = "budget")
@IdClass(value = Budget.PK.class)
public class Budget {

    @Id
    @Column(name = "account_id")
    private long accountId;

    @Id
    @Column(name = "category_master_id")
    private long categoryMasterId;

    @Id
    @Column(name = "date")
    private Date date;

    @Column(name = "price")
    private int price;

    public Budget(Long accountId, Long categoryMasterId, Date date, int price) {
        this.accountId = accountId;
        this.categoryMasterId = categoryMasterId;
        this.date = date;
        this.price = price;
    }

    @Data
    @NoArgsConstructor
    public static class PK implements Serializable {
        private long accountId;
        private long categoryMasterId;
        private Date date;
    }
}
