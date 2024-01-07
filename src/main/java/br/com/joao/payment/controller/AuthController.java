package br.com.joao.payment.controller;

import br.com.joao.payment.dto.UserDTO;
import br.com.joao.payment.entity.User;
import br.com.joao.payment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.registerUser(new User(userDTO)));
    }

}
