package springboot2.controller;

import springboot2.exception.ResourceNotFoundException;
import springboot2.model.Article;
import springboot2.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/articles/{idArticle}")
    public ResponseEntity<Article> getArticleById(@PathVariable(value ="idArticle") Long idArticle)
        throws ResourceNotFoundException {
        Article article = articleRepository.findById(idArticle)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + idArticle));
        return ResponseEntity.ok().body(article);
    }

    @PostMapping("/articles")
    public Article createArticle(@Valid @RequestBody Article article) {
        return articleRepository.save(article);
    }

    @PutMapping("/articles/{idArticle}")
    public ResponseEntity<Article> updateArticle(@PathVariable(value ="idArticle") Long idArticle,
                                                  @Valid @RequestBody Article articleDetails) throws ResourceNotFoundException {
        Article article = articleRepository.findById(idArticle)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found for this id :: " + idArticle ));

        article.setTitle(articleDetails.getTitle());
        article.setPrice(articleDetails.getPrice());

        final Article updatedArticle = articleRepository.save(article);
        return ResponseEntity.ok(updatedArticle);
    }

    @DeleteMapping("/articles/{idArticle}")
    public Map<String, Boolean> deleteArticle (@PathVariable(value = "idArticle") Long idArticle )
        throws ResourceNotFoundException {
        Article article = articleRepository.findById(idArticle)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found for this id ::" + idArticle));

        articleRepository.delete(article);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
