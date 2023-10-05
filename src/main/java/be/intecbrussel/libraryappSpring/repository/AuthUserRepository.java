package be.intecbrussel.libraryappSpring.repository;

import be.intecbrussel.libraryappSpring.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, String> {

    //Optional<AuthUser> findByEmail(String email);
}
