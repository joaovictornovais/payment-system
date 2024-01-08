package br.com.joao.payment.entity;

import br.com.joao.payment.dto.EmailDTO;
import br.com.joao.payment.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
