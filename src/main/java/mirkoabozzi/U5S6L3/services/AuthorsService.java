package mirkoabozzi.U5S6L3.services;


import mirkoabozzi.U5S6L3.entities.Author;
import mirkoabozzi.U5S6L3.exceptions.NotFoundException;
import mirkoabozzi.U5S6L3.repositories.AuthorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthorsService {
    @Autowired
    private AuthorsRepository authorsRepository;
    private List<Author> authorList = new ArrayList<>();

    //GET ALL
    public List<Author> findAll() {
        return this.authorList;
    }

    // GET BY ID
    public Author findById(UUID id) {
        Author found = this.authorList.stream().filter(author -> author.getId() == id).findFirst().orElse(null);
        if (found == null) throw new NotFoundException(id);
        else return found;
    }

    //POST
    public Author saveAuthor(Author payload) {
        payload.setAvatar("https://ui-avatars.com/api/?name=" + payload.getName() + "+" + payload.getSurname());
        this.authorList.add(payload);
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
        this.authorList.remove(found);
    }

}
