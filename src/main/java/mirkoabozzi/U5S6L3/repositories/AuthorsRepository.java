package mirkoabozzi.U5S6L3.repositories;

import mirkoabozzi.U5S6L3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorsRepository extends JpaRepository<Author, UUID> {
    boolean existsByEmail(String email);
}
