package br.com.joao.payment.domain.email;

public record EmailDTO(String ownerRef, String emailFrom, String emailTo, String subject, String text) {
}
