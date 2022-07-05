package com.example.heyder.modelos;


public class Titular {

    private String nome;
    private Email email;
    private CPF cpf;

    public Titular(String nome, Email email, CPF cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }


    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Email getEmail() {
        return email;
    }
    public void setEmail(Email email) {
        this.email = email;
    }
    
    public String getCpf() {
        return cpf.getNumero();
    }
    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }
    
}
