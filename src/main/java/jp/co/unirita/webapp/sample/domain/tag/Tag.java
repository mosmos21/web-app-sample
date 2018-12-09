package jp.co.unirita.webapp.sample.domain.tag;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@ToString
@NoArgsConstructor
@Table(name = "tag")
@IdClass(value = Tag.PK.class)
public class Tag {

    @Id
    @Column(name = "expense_id")
    private long expenseId;

    @Id
    @Column(name = "tag_master_id")
    private long tagMasterId;

    @Data
    @NoArgsConstructor
    public static class PK implements Serializable {
        private long expenseId;
        private long tagMasterId;
    }
}
