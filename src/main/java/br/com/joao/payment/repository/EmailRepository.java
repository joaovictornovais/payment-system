package br.com.joao.payment.repository;

import br.com.joao.payment.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
