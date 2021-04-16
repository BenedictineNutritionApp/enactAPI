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
    public ResponseEntity<?> upload(@Valid @RequestParam("title") String title, @RequestParam("author")
            String author, @RequestParam("topic") String topic, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("MADE IT TO ARTICLE UPLOAD");
        System.out.println(file.getOriginalFilename());
        System.out.println(title);
        System.out.println(author);
        System.out.println(topic);
        System.out.println(file.getName());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Article article = new Article(fileName, file.getBytes());
        article.setCreated(new Date());
        article.setUpdated(new Date());
        articleRepository.save(article);
        return ResponseEntity.ok(new MessageResponse("Article uploaded successfully!"));
    }

    @PreAuthorize("hasRole('SUPER') or hasRole('MASTER')")
    @PostMapping("/edit")
    public ResponseEntity<?> upload(@Valid @RequestParam("id") Long id, @RequestParam("title") String title, @RequestParam("author")
            String author, @RequestParam("topic") String topic, @RequestParam("file") MultipartFile file) throws IOException {
        if(file.getContentType().equals("application/pdf")) {
            System.out.println("MADE IT TO ARTICLE EDIT");
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getContentType());
            System.out.println(id);
            System.out.println(title);
            System.out.println(author);
            System.out.println(topic);
            System.out.println(file.getName());
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Article article = new Article(fileName, file.getBytes());
            article.setCreated(new Date());
            article.setUpdated(new Date());
            articleRepository.save(article);
            return ResponseEntity.ok(new MessageResponse("Article uploaded successfully!"));
        } else {
            System.out.println("BUMMERMAN");

            return ResponseEntity.ok(new MessageResponse("Article uploaded successfully!"));
        }

    }



    @PreAuthorize("hasRole('SUPER') or hasRole('MASTER')")
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@Valid @RequestParam("title") String title, @RequestParam("author")
            String author, @RequestParam("topic") String topic, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("MADE IT TO ARTICLE UPLOAD");
        System.out.println(file.getOriginalFilename());
        System.out.println(title);
        System.out.println(author);
        System.out.println(topic);
        System.out.println(file.getName());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Article article = new Article(fileName, "author", "subject", "type", file.getBytes(), false);
        article.setCreated(new Date());
        article.setUpdated(new Date());
        articleRepository.save(article);
        return ResponseEntity.ok(new MessageResponse("Article uploaded successfully!"));
    }

    @PreAuthorize("hasRole('SUPER') or hasRole('MASTER')")
    @PostMapping("/edit")
    public ResponseEntity<?> upload(@Valid @RequestParam("id") Long id, @RequestParam("title") String title, @RequestParam("author")
            String author, @RequestParam("topic") String topic, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.getContentType().equals("application/pdf")) {
            System.out.println("MADE IT TO ARTICLE EDIT");
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getContentType());
            System.out.println(id);
            System.out.println(title);
            System.out.println(author);
            System.out.println(topic);
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
//TODO CHANGE VISIBILITY
    //TODO
}
