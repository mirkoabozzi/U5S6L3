package mirkoabozzi.U5S6L3.services;


import mirkoabozzi.U5S6L3.entities.Author;
import mirkoabozzi.U5S6L3.exceptions.NotFoundException;
import mirkoabozzi.U5S6L3.exceptions.ValidationException;
import mirkoabozzi.U5S6L3.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorsService {
    @Autowired
    private AuthorsRepository authorsRepository;

    //GET ALL
    public Page<Author> findAll(int page, int size, String shortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(shortBy));
        return this.authorsRepository.findAll(pageable);
    }

    // GET BY ID
    public Author findById(UUID id) {
        return this.authorsRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    //POST
    public Author saveAuthor(Author payload) {
        if (authorsRepository.existsByEmail(payload.getEmail()))
            throw new ValidationException("Email " + payload.getEmail() + " già presente nel DB");
        payload.setAvatar("https://ui-avatars.com/api/?name=" + payload.getName() + "+" + payload.getSurname());
        this.authorsRepository.save(payload);
        return payload;
    }

    //PUT
    public Author findByIdAndUpdate(UUID id, Author newAuthor) {
        Author found = this.findById(id);
        found.setName(newAuthor.getName());
        found.setSurname(newAuthor.getSurname());
        found.setEmail(newAuthor.getEmail());
        found.setBirthDate(newAuthor.getBirthDate());
        return found;
    }

    //DELETE
    public void findByIdAndDelete(UUID id) {
        Author found = this.findById(id);
        this.authorsRepository.delete(found);
    }

}
