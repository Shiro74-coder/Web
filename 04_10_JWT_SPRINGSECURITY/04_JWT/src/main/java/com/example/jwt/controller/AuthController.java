package com.example.jwt.controller;

import com.example.jwt.entity.Role;
import com.example.jwt.entity.User;
import com.example.jwt.repo.RoleRepository;
import com.example.jwt.repo.UserRepository;
import com.example.jwt.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> req) {
        String username = req.get("username");
        String password = req.get("password");
        if (userRepo.existsByUsername(username)) {
            return ResponseEntity.badRequest().body(Map.of("error","username already exists"));
        }
        User u = new User(username, encoder.encode(password));
        Role roleUser = roleRepo.findByName("ROLE_USER").orElseGet(() -> roleRepo.save(new Role("ROLE_USER")));
        u.getRoles().add(roleUser);
        userRepo.save(u);
        return ResponseEntity.ok(Map.of("message","registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> req) {
        String username = req.get("username");
        String password = req.get("password");
        authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        var user = userRepo.findByUsername(username).orElseThrow();
        String token = jwtService.generateToken(new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(),
                user.getRoles().stream().map(r -> new org.springframework.security.core.authority.SimpleGrantedAuthority(r.getName())).toList()
        ));
        return ResponseEntity.ok(Map.of("token", token));
    }
}
