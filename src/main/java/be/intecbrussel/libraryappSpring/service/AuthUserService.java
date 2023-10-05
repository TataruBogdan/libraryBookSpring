package be.intecbrussel.libraryappSpring.service;


import be.intecbrussel.libraryappSpring.model.AuthUser;
import be.intecbrussel.libraryappSpring.repository.AuthUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthUserService {

    private final AuthUserRepository authUserRepository;

    public AuthUserService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    public Optional<AuthUser> addUser(AuthUser user) {
        return Optional.of(authUserRepository.save(user));
    }

    public List<AuthUser> findAllUsers(){
        return authUserRepository.findAll();
    }
}
