package com.example.heyder.modelos;

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
        if ( endereco == null || !endereco.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$") ) {
            throw new IllegalArgumentException( "Email invalido" );
        }
        this.endereco = endereco;
    }

    /**
     * Método que retorna o email do cliente.
     * @return String
     */

    public String getEndereco() {
        return endereco;
    }

}
