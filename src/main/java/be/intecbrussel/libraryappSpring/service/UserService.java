package be.intecbrussel.libraryappSpring.service;


import be.intecbrussel.libraryappSpring.model.User;
import be.intecbrussel.libraryappSpring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> addUser(User user) {
        return Optional.of(userRepository.save(user));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
