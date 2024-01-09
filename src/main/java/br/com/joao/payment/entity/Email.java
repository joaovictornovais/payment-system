package br.com.joao.payment.entity;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import br.com.joao.payment.dto.EmailDTO;
import br.com.joao.payment.enums.StatusEmail;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_email")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;

    public Email(EmailDTO emailDTO) {
        BeanUtils.copyProperties(emailDTO, this);
    }

}
