package mirkoabozzi.U5S6L3.services;

import mirkoabozzi.U5S6L3.entities.BlogPost;
import mirkoabozzi.U5S6L3.exceptions.NotFoundException;
import mirkoabozzi.U5S6L3.repositories.BlogPostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BlogPostsService {
    @Autowired
    private BlogPostsRepository blogPostsRepository;
    private List<BlogPost> blogPostList = new ArrayList<>();

    //GET ALL
    public List<BlogPost> findAll() {
        return this.blogPostList;
    }

    //GET BLOGPOST
    public BlogPost findById(UUID id) {
        BlogPost found = this.blogPostList.stream().filter(blogPost -> blogPost.getId() == id).findFirst().orElse(null);
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    // POST
    public BlogPost saveBlogPost(BlogPost payload) {
        payload.setCover("http://localhost:8080/" + payload.getTitle());
        this.blogPostList.add(payload);
        return payload;
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
        this.blogPostList.remove(found);
    }

}
