package be.intecbrussel.libraryappSpring.controller;

import be.intecbrussel.libraryappSpring.model.AuthUser;
import be.intecbrussel.libraryappSpring.service.AuthUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    private AuthUserService authUserService;

    public UserController(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @GetMapping("users/{username}")
    public List<AuthUser> getAllUsers(@PathVariable String username){
        return authUserService.findAllUsers();
    }

}
