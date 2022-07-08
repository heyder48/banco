package com.example.heyder.modelos;

/**
 * A classe telefone é uma representação de um telefone.
 */
public class Telefone {
    /**
     * O ddd do telefone.
     * ddd é um número de 2 dígitos.
     * ddd é o código de área do telefone.
     *
     */
    private String ddd;

    /**
     * O número do telefone.
     * numero é um número de 8 ou 9 dígitos.
    */
    private String numero;

    /**
     * Construtor da classe Telefone
     * Valida se o telefone é não nulo.
     * Valida se o telefone é no formato de telefone usando regex.
     * @param ddd
     * @param numero
     */
    public Telefone(String ddd, String numero) {
        if (ddd == null || numero == null || !ddd.matches("^[0-9]{2}$") || !numero.matches("^([0-9]{8}|[0-9]{9})$")) {
            throw new IllegalArgumentException("Telefone invalido");
        }
        this.ddd = ddd;
        this.numero = numero;
    }

    
    /** 
     * @return String
     */
    public String getDdd() {
        return ddd;
    }

    
    /** 
     * @param ddd
     */
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    
    /** 
     * @return String
     */
    public String getNumero() {
        return numero;
    }

    
    /** 
     * @param numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

}
