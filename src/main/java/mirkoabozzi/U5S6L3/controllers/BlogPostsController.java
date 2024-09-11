package mirkoabozzi.U5S6L3.controllers;

import mirkoabozzi.U5S6L3.entities.BlogPost;
import mirkoabozzi.U5S6L3.services.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogPosts")
public class BlogPostsController {

    @Autowired
    BlogPostsService blogPostsService;

    //GET ALL
    @GetMapping
    private List<BlogPost> getAllBlogPosts() {
        return blogPostsService.findAll();
    }

    //GET BY ID
    @GetMapping("/{id}")
    private BlogPost findById(@PathVariable UUID id) {
        return blogPostsService.findById(id);
    }

    //POST
    @PostMapping
    private BlogPost createBlogPosts(@RequestBody BlogPost body) {
        return blogPostsService.saveBlogPost(body);
    }

    //PUT
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    private BlogPost findByIdAndUpdate(@PathVariable UUID id, @RequestBody BlogPost body) {
        return blogPostsService.findByIdAndUpdate(id, body);
    }

    //DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void findByIdAndDelete(@PathVariable UUID id) {
        blogPostsService.findBiIdAndDelete(id);
    }

}
