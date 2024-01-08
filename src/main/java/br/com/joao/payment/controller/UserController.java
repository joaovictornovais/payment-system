package br.com.joao.payment.controller;

import br.com.joao.payment.dto.UserDTO;
import br.com.joao.payment.entity.User;
import br.com.joao.payment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}", params = "verify")
    public ResponseEntity<String> active(@PathVariable String id, @RequestParam String verify) {
        if (userService.activeUser(id, verify)) return ResponseEntity.ok("User activated!");
        return ResponseEntity.badRequest().body("Wrong code or user already activated!");
    }

}
