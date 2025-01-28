package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import org.springframework.web.server.ResponseStatusException;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private static List<Post> posts = Data.getPosts();

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @PostMapping("/posts")// создание поста
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @GetMapping("/posts") // Список постов
    public List<Post> index() {
        return posts;
    }

    @GetMapping("/posts/{id}")//просмотр конкретного поста
    public Optional<Post> show(@PathVariable String id) {
        var post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return post;
    }

    @PutMapping("/posts/{id}") // Обновление страницы
    public Post update(@PathVariable String id, @RequestBody Post data) {
        var maybePost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (maybePost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with id '" + id + "' not found");
        }
        var post = maybePost.get();
        post.setTitle(data.getTitle());
        post.setBody(data.getBody());
        return post;
    }
    @DeleteMapping("/posts/{id}") // Удаление страницы
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
// END

