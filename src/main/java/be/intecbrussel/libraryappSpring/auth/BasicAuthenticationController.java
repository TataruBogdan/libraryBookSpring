package be.intecbrussel.libraryappSpring.auth;


import be.intecbrussel.libraryappSpring.model.AuthUser;
import be.intecbrussel.libraryappSpring.model.dto.LoginRequest;
import be.intecbrussel.libraryappSpring.model.dto.LoginResponse;
import be.intecbrussel.libraryappSpring.security.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/basicauth")
public class BasicAuthenticationController {

    private final RegisterService registerService;

    public BasicAuthenticationController(RegisterService registerService) {
        this.registerService = registerService;
    }

    // having the proper authentication details, then you would have written response back
    @GetMapping(path = "/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        Optional<LoginResponse> loginResponse = registerService.getByEmail(loginRequest.getEmail());

        if(loginResponse.isPresent()) {
            return ResponseEntity.ok(loginResponse.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/register")
    public ResponseEntity<LoginRequest> register(@RequestBody LoginRequest loginRequest) {
        registerService.createUser(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/delete/{email}")
    public ResponseEntity<LoginResponse> delete(@PathVariable String email){

        Optional<LoginResponse> foundUserByEmail = registerService.getByEmail(email);
        if (foundUserByEmail.isPresent()){
            Optional<AuthUser> deleteAuthUser = registerService.delete(email);
            return ResponseEntity.ok(foundUserByEmail.get());

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

//    @PatchMapping(path = "/update/{email}")
//    public ResponseEntity<LoginResponse> update(@PathVariable String email, @RequestBody LoginRequest loginRequest) {
//
//        Optional<LoginResponse> foundUserByEmail = registerService.getByEmail(email);
//
//        if (foundUserByEmail.isPresent()) {
//            LoginResponse bdLoginResponse = foundUserByEmail.get();
//
//            bdLoginResponse.set(loginRequest.getPassword());
//
//            registerService.save(email);
//
//        }
//    }

}
