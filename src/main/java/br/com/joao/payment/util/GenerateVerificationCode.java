package br.com.joao.payment.util;

import java.security.SecureRandom;

public class GenerateVerificationCode {

    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generate(int lenght) {
        StringBuilder sb = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i=0; i<lenght; i++) {
            int index = secureRandom.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

}
