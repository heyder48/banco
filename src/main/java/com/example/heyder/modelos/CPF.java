package com.example.heyder.modelos;

/**
 * A classe CPF representa um CPF.
 * CPF é um número de identificação da pessoa física brasileira.
 * @author heyder
 */
 
public class CPF {

    /**
     * O atributo numero representa o CPF.
     */
    private String numero;

    /**
     * Construtor da classe CPF.
     * A validação do CPF é feita no construtor.
     * Não perimite CPF vazio.
     * Verifica se o CPF possui 11 dígitos.
     * @param numero
     * @throws IllegalArgumentException
     */

    public CPF(String numero) {
        if(numero == null) {
            throw new IllegalArgumentException("O número do CPF não pode ser nulo");
        }
        if(numero.length() != 11) {
            throw new IllegalArgumentException("O número do CPF deve ter 11 dígitos");
        }
        this.numero = numero;
    }

    /**
     * Método que retorna o número do CPF.
     * @return String
     */

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
