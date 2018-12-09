package jp.co.unirita.webapp.sample.form.chart;

import jp.co.unirita.webapp.sample.domain.budget.Budget;
import jp.co.unirita.webapp.sample.domain.expense.Expense;
import jp.co.unirita.webapp.sample.domain.master.category.CategoryMaster;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class ExpenseChart {
    private List<String> labelList;
    private List<String> backgroundColorList;
    private List<String> hoverBackgroundColorList;
    private List<Integer> dataList;

    public ExpenseChart(List<CategoryMaster> categoryMasterList, List<Expense> budgetList) {
        Map<Long, String> map = categoryMasterList.stream()
                .collect(Collectors.toMap(CategoryMaster::getId, CategoryMaster::getName));
        labelList = budgetList.stream()
                .map(b -> map.get(b.getCategoryMasterId()))
                .collect(Collectors.toList());
        backgroundColorList = Arrays.asList("#FF6384", "#36A2EB", "#FFCE56");
        hoverBackgroundColorList = Arrays.asList( "#FF6384", "#36A2EB", "#FFCE56" );
        dataList = budgetList.stream()
                .map(Expense::getPrice)
                .collect(Collectors.toList());
    }

    public int sum() {
        return dataList.stream().mapToInt(Integer::new).sum();
    }
}
