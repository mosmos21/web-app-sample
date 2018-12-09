package jp.co.unirita.webapp.sample.domain.master.category;

import jp.co.unirita.webapp.sample.domain.budget.Budget;
import jp.co.unirita.webapp.sample.domain.expense.Expense;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@ToString
@NoArgsConstructor
@Table(name = "category_master")
public class CategoryMaster {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_master_id")
    private List<Budget> budgetList;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_master_id")
    private Set<Expense> expenseList;

    public CategoryMaster(String name) {
        this.name = name;
    }
}
