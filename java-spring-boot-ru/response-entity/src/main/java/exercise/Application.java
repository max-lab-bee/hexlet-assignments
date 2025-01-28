package exercise;


import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


import exercise.model.Post;

import static org.springframework.http.ResponseEntity.*;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
    // BEGIN

    public ResponseEntity<List<Post>> index() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(posts.size()));
        return ok().headers(headers).body(posts);
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post postData) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(post -> {
                    post.setTitle(postData.getTitle());
                    post.setBody(postData.getBody());
                    return ResponseEntity.ok(post);
                })
                .orElse(ResponseEntity.noContent().build());
    }
}
        // END


