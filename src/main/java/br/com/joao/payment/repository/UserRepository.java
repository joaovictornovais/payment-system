package br.com.joao.payment.repository;

import br.com.joao.payment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByEmail(String email);

}
