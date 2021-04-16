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
                                         @RequestParam("file") MultipartFile file) {
        if (file.getContentType().equals("application/pdf")) {
            // this is manually converted because the front end can't send booleans in form data
            boolean visible = isVisible.equalsIgnoreCase("true");
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getContentType());
            System.out.println(articleName);
            System.out.println(articleAuthor);
            System.out.println(articleSubject);
            System.out.println(articleType);
            System.out.println(isVisible);
            //create new article
            //save article to db
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
                                         @RequestParam("file") MultipartFile file) {
        if (file.getContentType().equals("application/pdf")) {
            // this is manually converted because the front end can't send booleans in form data
            boolean visible = isVisible.equalsIgnoreCase("true");
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getContentType());
            System.out.println(id);
            System.out.println(articleName);
            System.out.println(articleAuthor);
            System.out.println(articleSubject);
            System.out.println(articleType);
            System.out.println(isVisible);
            //find existing article
            //change necessary fields
            //save article
            return ResponseEntity.ok(new MessageResponse("Article uploaded successfully!"));
        } else {

            return ResponseEntity.ok(new MessageResponse("Invalid file type"));
        }
    }

    @PreAuthorize("hasRole('SUPER') or hasRole('MASTER')")
    @Transactional
    @DeleteMapping(value = "/delete/{id}")
    public void deleteUserGiIssues(@PathVariable Long id) {
        articleRepository.deleteArticleById(id);

    }

}
