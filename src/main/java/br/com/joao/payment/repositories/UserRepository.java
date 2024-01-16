package br.com.joao.payment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.joao.payment.domain.user.User;


public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByEmail(String email);

}
