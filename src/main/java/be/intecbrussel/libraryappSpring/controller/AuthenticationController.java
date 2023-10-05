package be.intecbrussel.libraryappSpring.controller;


import be.intecbrussel.libraryappSpring.model.AuthUser;
import be.intecbrussel.libraryappSpring.model.dto.LoginRequest;
import be.intecbrussel.libraryappSpring.model.dto.LoginResponse;
import be.intecbrussel.libraryappSpring.security.JwtUtil;
import be.intecbrussel.libraryappSpring.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RegisterService registerService;


    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, RegisterService registerService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.registerService = registerService;
        System.out.println("s-a initializat un @bean AuthController");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            String email = authentication.getName();
            AuthUser user = new AuthUser(email, "");
            String token = jwtUtil.createToken(user);
            LoginResponse loginResponse = new LoginResponse(email, token);
            return ResponseEntity.ok(loginResponse);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username or password");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody LoginRequest loginRequest) {
        registerService.createUser(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok().build();
    }


    // having the proper authentication details, then you would have written response back
//    @GetMapping(path = "/login")
//    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
//
//        Optional<LoginResponse> loginResponse = registerService.getByEmail(loginRequest.getEmail());
//
//        if(loginResponse.isPresent()) {
//            return ResponseEntity.ok(loginResponse.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<LoginRequest> register(@RequestBody LoginRequest loginRequest) {
//        registerService.createUser(loginRequest.getEmail(), loginRequest.getPassword());
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping(path = "/delete/{email}")
//    public ResponseEntity<LoginResponse> delete(@PathVariable String email){
//
//        Optional<LoginResponse> foundUserByEmail = registerService.getByEmail(email);
//        if (foundUserByEmail.isPresent()){
//            Optional<AuthUser> deleteAuthUser = registerService.delete(email);
//            return ResponseEntity.ok(foundUserByEmail.get());
//
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//    }

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
