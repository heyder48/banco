package com.example.heyder.modelos;

/**
 * A Classe CNPJ representa um CNPJ.
 * CNPJ é um número de identificação da pessoa jurídica brasileira.
 * @author heyder
 */
 
public class CNPJ {

    /**
     * O atributo numero representa o CNPJ.
     */
    private String numero;

    /**
     * Construtor da classe CNPJ.
     * A validação do CNPJ é feita no construtor.
     * Não perimite CNPJ vazio.
     * Verifica se o CNPJ possui 14 dígitos.
     * @param numero
     */
    public CNPJ(String numero) {
        if (numero == null) {
            throw new IllegalArgumentException("O número do CNPJ não pode ser nulo");
        }
        if (numero.length() != 14) {
            throw new IllegalArgumentException("O número do CNPJ deve ter 14 dígitos");
        }
        this.numero = numero;
    }

    /**
     * Método que retorna o número do CNPJ.
     * @return String
     */

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
}
