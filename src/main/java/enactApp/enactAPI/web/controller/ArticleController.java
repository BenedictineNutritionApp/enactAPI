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

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
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

    @PreAuthorize("hasRole('SUPER') or hasRole('MASTER')")
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@Valid @RequestParam("articleName") String title, @RequestParam("articleAuthor")
            String author, @RequestParam("articleSubject") String subject, @RequestParam("articleType") String type, @RequestParam("isVisible") String visibility, @RequestParam("file") MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        Article article = new Article();
        Article article = new Article(fileName, "author", "subject", "type", file.getBytes(), false);
        article.setCreated(new Date());
        article.setUpdated(new Date());
        articleRepository.save(article);
        return ResponseEntity.ok(new MessageResponse("Article uploaded successfully!"));
    }


    @PreAuthorize("hasRole('SUPER') or hasRole('MASTER')")
    @PostMapping("/edit")
    public ResponseEntity<?> upload(@Valid @RequestParam("id") Long id, @Valid @RequestParam("articleName") String title, @RequestParam("articleAuthor")
            String author, @RequestParam("articleSubject") String subject, @RequestParam("articleType") String type, @RequestParam("isVisible") String visibility, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.getContentType().equals("application/pdf")) {
            System.out.println("MADE IT TO ARTICLE EDIT");
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getContentType());
            System.out.println(id);
            System.out.println(title);
            System.out.println(author);
            System.out.println(subject);
            System.out.println(file.getName());
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Article article = new Article(fileName, "author", "subject", "type", file.getBytes(), false);
            article.setCreated(new Date());
            article.setUpdated(new Date());
            articleRepository.save(article);
            return ResponseEntity.ok(new MessageResponse("Article uploaded successfully!"));
        } else {
            System.out.println("BUMMERMAN");

            return ResponseEntity.ok(new MessageResponse("Article uploaded successfully!"));
        }
    }
    //TODO
}
