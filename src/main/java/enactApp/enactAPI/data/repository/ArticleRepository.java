package enactApp.enactAPI.data.repository;

import enactApp.enactAPI.data.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAll();
    void deleteArticleById(Long id);

    Optional<Article> findArticleById(Long id);

    List<Article> findAllByArticleAuthorLike(String searchString);

    List<Article> findAllByArticleNameLike(String searchString);

    List<Article> findAllByArticleSubjectLike(String searchString);
    

}
