package br.com.joao.payment.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joao.payment.domain.user.AuthenticationDTO;
import br.com.joao.payment.domain.user.LoginResponseDTO;
import br.com.joao.payment.domain.user.RegisterDTO;
import br.com.joao.payment.domain.user.User;
import br.com.joao.payment.repositories.UserRepository;
import br.com.joao.payment.services.TokenService;
import br.com.joao.payment.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	private final AuthenticationManager authenticationManger;
	private final UserRepository userRepository;
	private final UserService userService;
	private final TokenService tokenService;
	
	public AuthenticationController(AuthenticationManager authenticationManager, 
			UserRepository userRepository, 
			UserService userService,
			TokenService tokenService) {
		this.authenticationManger = authenticationManager;
		this.userRepository = userRepository;
		this.userService = userService;
		this.tokenService = tokenService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		var auth = authenticationManger.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody RegisterDTO data) {
		if (userRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(userService.registerUser(new User(data)));
	}

}
