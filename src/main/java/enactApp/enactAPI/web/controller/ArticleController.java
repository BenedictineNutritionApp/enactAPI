package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.Article;
import enactApp.enactAPI.data.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ArticleController {
    private final ArticleRepository articleRepository;


    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping(value = "/api/article/all")
    public List<Article> getAllArticles(){
        List<Article> articleList = articleRepository.findAll();
        return articleList;
    }
}
