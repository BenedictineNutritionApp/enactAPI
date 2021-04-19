package enactApp.enactAPI.web.controller;

import enactApp.enactAPI.data.model.*;
import enactApp.enactAPI.data.repository.ArticleRepository;
import enactApp.enactAPI.web.payload.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleRepository articleRepository;


    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping(value = "/all")
    public List<Article> getAllArticles() {
        List<Article> articleList = articleRepository.findAll();
        return articleList;
    }

    @PreAuthorize("hasRole('SUPER') or hasRole('MASTER')")
    @PostMapping("/upload")
    public ResponseEntity<?> createArticle(@Valid
                                           @RequestParam("articleName") String articleName,
                                           @RequestParam("articleAuthor") String articleAuthor,
                                           @RequestParam("articleSubject") String articleSubject,
                                           @RequestParam("articleType") String articleType,
                                           @RequestParam("isVisible") String isVisible,
                                           @RequestParam("file") MultipartFile file) throws IOException {
        if (file.getContentType().equals("application/pdf")) {
            boolean visible = isVisible.equalsIgnoreCase("true");
            Article article = new Article(articleName, articleAuthor, articleSubject, articleType, file.getBytes(), visible);
            article.setCreated(new Date());
            article.setUpdated(new Date());
            articleRepository.save(article);
            return ResponseEntity.ok(new MessageResponse("Article uploaded successfully!"));
        } else {
            return ResponseEntity.ok(new MessageResponse("Invalid file type"));
        }
    }


    @PreAuthorize("hasRole('SUPER') or hasRole('MASTER')")
    @PostMapping("/edit")
    public ResponseEntity<?> editArticle(@Valid @RequestParam("id") Long id,
                                         @RequestParam("articleName") String articleName,
                                         @RequestParam("articleAuthor") String articleAuthor,
                                         @RequestParam("articleSubject") String articleSubject,
                                         @RequestParam("articleType") String articleType,
                                         @RequestParam("isVisible") String isVisible,
                                         @RequestParam("file") MultipartFile file) throws IOException {
        if (file.getContentType().equals("application/pdf")) {
            Optional<Article> optionalArticle = articleRepository.findArticleById(id);
            if (optionalArticle.isPresent()) {
                Article article = optionalArticle.get();
                article.setArticleName(articleName);
                article.setArticleAuthor(articleAuthor);
                article.setArticleSubject(articleSubject);
                article.setArticleType(articleType);
                article.setVisible(isVisible.equalsIgnoreCase("true"));
                article.setUpdated(new Date());
                articleRepository.save(article);
                return ResponseEntity.ok(new MessageResponse("Article uploaded successfully!"));
            } else {
                return ResponseEntity.ok(new MessageResponse("Error"));

            }

        } else {
            System.out.println("BUMMERMAN");

            return ResponseEntity.ok(new MessageResponse("Invalid file type"));
        }
    }

    @PreAuthorize("hasRole('SUPER') or hasRole('MASTER')")
    @Transactional
    @DeleteMapping(value = "/delete/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleRepository.deleteArticleById(id);
    }
}
