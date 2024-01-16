package br.com.joao.payment.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.joao.payment.services.UserService;

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
    
    @GetMapping
    public ResponseEntity<String> get() {
    	return ResponseEntity.ok("Olá!");
    }

}
