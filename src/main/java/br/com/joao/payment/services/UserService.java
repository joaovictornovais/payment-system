package br.com.joao.payment.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.joao.payment.domain.email.Email;
import br.com.joao.payment.domain.user.User;
import br.com.joao.payment.repositories.UserRepository;
import br.com.joao.payment.utils.EmailTemplate;
import br.com.joao.payment.utils.GenerateVerificationCode;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("This email is already in use!");

        String enctryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(enctryptedPassword);

        String verificationCode = GenerateVerificationCode.generate(64);
        user.setVerificationCode(verificationCode);

        userRepository.save(user);

        Email email = new Email();

        email.setOwnerRef("João Victor");
        email.setEmailFrom("jneadgamer@gmail.com");
        email.setEmailTo(user.getEmail());
        email.setSubject("Falta pouco! ative sua conta");

        String link = "http://localhost:8080/users/" + user.getId() + "?verify=" + user.getVerificationCode();

        email.setText(EmailTemplate.generateEmail(user.getName(), link));

        emailService.sendEmail(email);

        return user;

    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public boolean activeUser(String id, String verificationCode) {
        User user = findById(id);
        if (!user.isEnabled() && verificationCode.equals(user.getVerificationCode())) {
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }
    
    public UserDetails findByEmail(String email) {
    	return userRepository.findByEmail(email);
    }
}
