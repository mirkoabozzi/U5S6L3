package mirkoabozzi.U5S6L3.controllers;

import mirkoabozzi.U5S6L3.entities.Author;
import mirkoabozzi.U5S6L3.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    @Autowired
    AuthorsService authorsService;

    // GET ALL
    @GetMapping
    private List<Author> getAllAuthors() {
        return authorsService.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    private Author findById(@PathVariable UUID id) {
        return authorsService.findById(id);
    }

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Author saveAuthor(@RequestBody Author body) {
        return authorsService.saveAuthor(body);
    }

    //PUT
    @PutMapping("/{id}")
    private Author findByIdAndUpdate(@PathVariable UUID id, @RequestBody Author body) {
        return authorsService.findByIdAndUpdate(id, body);
    }

    //DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void findByIdAndDelete(@PathVariable UUID id) {
        authorsService.findByIdAndDelete(id);
    }
}
