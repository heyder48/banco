package com.example.heyder.modelos;

/**
 * Representa o titular, ou seja, o representante legal da empresa.
 * @author heyder
 */

public class Titular {

    private String nome;
    private Email email;
    private CPF cpf;

    public Titular(String nome, Email email, CPF cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }


    
    /** 
     * @return String
     */
    public String getNome() {
        return nome;
    }
    
    /** 
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /** 
     * @return Email
     */
    public Email getEmail() {
        return email;
    }
    
    /** 
     * @param email
     */
    public void setEmail(Email email) {
        this.email = email;
    }
    
    
    /** 
     * @return String
     */
    public String getCpf() {
        return cpf.getNumero();
    }
    
    /** 
     * @param cpf
     */
    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }
    
}
