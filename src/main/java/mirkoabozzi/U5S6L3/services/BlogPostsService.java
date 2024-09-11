package mirkoabozzi.U5S6L3.services;

import mirkoabozzi.U5S6L3.entities.Author;
import mirkoabozzi.U5S6L3.entities.BlogPost;
import mirkoabozzi.U5S6L3.entities.BlogPostsPayload;
import mirkoabozzi.U5S6L3.exceptions.NotFoundException;
import mirkoabozzi.U5S6L3.repositories.BlogPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogPostsService {
    @Autowired
    private BlogPostsRepository blogPostsRepository;
    @Autowired
    private AuthorsService authorsService;

    //GET ALL
    public List<BlogPost> findAll() {
        return this.blogPostsRepository.findAll();
    }

    //GET BLOGPOST
    public BlogPost findById(UUID id) {
        return this.blogPostsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    // POST
    public BlogPost saveBlogPost(BlogPostsPayload payload) {
        BlogPost bp = new BlogPost();
        Author authorFound = this.authorsService.findById(payload.getAuthorId());
        bp.setAuthor(authorFound);
        bp.setCategory(payload.getCategory());
        bp.setContent(payload.getContent());
        bp.setReadingTime(payload.getReadingTime());
        bp.setTitle(payload.getTitle());
        bp.setCover("http://localhost:8080/" + payload.getTitle());
        this.blogPostsRepository.save(bp);
        return bp;
    }

    //PUT
    public BlogPost findByIdAndUpdate(UUID id, BlogPost newBlogPost) {
        BlogPost found = this.findById(id);
        found.setCategory(newBlogPost.getCategory());
        found.setTitle(newBlogPost.getTitle());
        found.setContent(newBlogPost.getContent());
        found.setReadingTime(newBlogPost.getReadingTime());
        return found;
    }

    //DELETE
    public void findBiIdAndDelete(UUID id) {
        BlogPost found = this.findById(id);
        this.blogPostsRepository.delete(found);
    }

}
