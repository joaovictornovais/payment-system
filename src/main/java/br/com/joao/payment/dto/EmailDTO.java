package br.com.joao.payment.dto;

public record EmailDTO(String ownerRef, String emailFrom, String emailTo, String subject, String text) {
}
