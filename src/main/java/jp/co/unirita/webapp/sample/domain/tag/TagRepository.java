package jp.co.unirita.webapp.sample.domain.tag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Tag.PK> {
}
