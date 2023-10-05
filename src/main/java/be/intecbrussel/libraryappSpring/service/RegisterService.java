package be.intecbrussel.libraryappSpring.service;

import be.intecbrussel.libraryappSpring.model.AuthUser;
import be.intecbrussel.libraryappSpring.model.dto.LoginRequest;
import be.intecbrussel.libraryappSpring.model.dto.LoginResponse;
import be.intecbrussel.libraryappSpring.repository.AuthUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RegisterService {

    private final AuthUserRepository authUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegisterService(AuthUserRepository authUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authUserRepository = authUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void createUser(String email, String password) {
        AuthUser authUser = new AuthUser(email, bCryptPasswordEncoder.encode(password));
        authUserRepository.save(authUser);
    }




//    public void createUser(String user, String password) {
//        // Hash the password using BCrypt
//
//        AuthUser authUser = new AuthUser(user, password);
//        authUserRepository.save(authUser);
//    }

//    public Optional<LoginResponse> getByEmail(String email) {
//
//        Optional<AuthUser> authUserRepositoryByEmail = authUserRepository.findByEmail(email);
//
//        Optional<LoginResponse> loginResponse = Optional.of(new LoginResponse("", ""));
//        if (authUserRepositoryByEmail.isPresent()){
//
//            loginResponse.get().setEmail(authUserRepositoryByEmail.get().getEmail());
//            loginResponse.get().setToken("token1"); // token builder
//        }
//        return loginResponse;
//    }

//    public Optional<AuthUser> delete(String email) {
//
//        Optional<AuthUser> authUserRepositoryByEmail = authUserRepository.findByEmail(email);
//        if (authUserRepositoryByEmail.isPresent()) {
//            authUserRepository.delete(authUserRepositoryByEmail.get());
//            return authUserRepositoryByEmail;
//        } else {
//            return Optional.empty();
//        }
//
//    }

//    public Optional<AuthUser> save(String email) {
//        Optional<AuthUser> authUserRepositoryByEmail = authUserRepository.findByEmail(email);
//        Optional<LoginResponse> loginResponse = Optional.empty();
//        if (authUserRepositoryByEmail.isPresent()){
//            authUserRepositoryByEmail.get().setPassword(authUser.getPassword());
//            authUserRepository.save(authUser);
//            return authUserRepositoryByEmail;
//        } else {
//            return Optional.empty();
//        }
//
//
//    }
}
