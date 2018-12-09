package jp.co.unirita.webapp.sample.domain.master.tag;

import jp.co.unirita.webapp.sample.domain.tag.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@ToString
@NoArgsConstructor
@Table(name = "tag_master")
public class TagMaster {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "tag_master_id")
    private Set<Tag> tagList;
}
