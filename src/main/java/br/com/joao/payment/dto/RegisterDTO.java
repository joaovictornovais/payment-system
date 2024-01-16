package br.com.joao.payment.dto;

import br.com.joao.payment.enums.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {

}
