package com.example.heyder.modelos;

import java.util.regex.Pattern;

/**
 * A classe Email é uma representação de um email.
 * @author heyder
 */
 
public class Email {

    /**
     * Atributo endereço representa o email do cliente.
     */
     
    private String endereco;

    /**
     * Construtor da classe Email
     * Valida se o email é não nulo e verifica se está no formato de email.
     * @param endereco
     */
    public Email( String endereco ) {
        if ( endereco == null || !isEmailValid(endereco) ) {
            throw new IllegalArgumentException( "Email invalido" );
        }
        this.endereco = endereco;
    }

    /**
     * Metodo que valida se o email é valido.
     * @return true se o email for valido, false se não for.
     */
     
    private boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    /**
     * Método que retorna o email do cliente.
     * @return String
     */

    public String getEndereco() {
        return endereco;
    }

}
