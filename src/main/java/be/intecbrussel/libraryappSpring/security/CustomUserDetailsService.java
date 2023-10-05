package be.intecbrussel.libraryappSpring.security;

import be.intecbrussel.libraryappSpring.model.AuthUser;
import be.intecbrussel.libraryappSpring.repository.AuthUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    public CustomUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    // change to email <- loadUserByUsername(String username)
    // this maped object will be used to config out SecurityConfig witch is a Configuration
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<AuthUser> authUserRepositoryByEmail = authUserRepository.findById(email);

        if (authUserRepositoryByEmail.isEmpty()){
            throw new UsernameNotFoundException(email + " not found");
        }

        AuthUser authUser = authUserRepositoryByEmail.get();

        // create a User from Spring core not our User ! based on the AuthUser
        // we buld this user for Spring Security
        // map email , password and isAdmin to userDetails from out authUser
        UserDetails userDetails = User.builder()
                .username(authUser.getEmail())
                .password(authUser.getPassword())
                .roles(authUser.isAdmin() ? "ADMIN" : "USER")
                .build();
        return userDetails;
    }
}
