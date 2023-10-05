package be.intecbrussel.libraryappSpring.repository;

import be.intecbrussel.libraryappSpring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
