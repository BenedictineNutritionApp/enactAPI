package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAll();
}
