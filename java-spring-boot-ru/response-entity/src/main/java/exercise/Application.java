package exercise;


import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import exercise.model.Post;


@SpringBootApplication
@RestController
@RequestMapping("/posts")
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
    // BEGIN

    @GetMapping
    public ResponseEntity<List<Post>> index() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(posts.size()));
        return ResponseEntity.ok().headers(headers).body(posts);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(post -> ResponseEntity.ok(post))
                .orElse(ResponseEntity.notFound().build());
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable String id) {
        Post as= new Post(id,"s,dnv","sjndv");
        posts.add(0,as);
        posts.remove(as);
        return ResponseEntity.ok().build();
    }
}

// END