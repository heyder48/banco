package com.example.heyder.modelos;



public class ClientePF extends Cliente {

    private CPF cpf;
    private String nome;
    

    

    public ClientePF(String nome, CPF cpf, Email email,
            TipoDePessoa tipoDePessoa, Endereco endereco) {
                
        super(email, tipoDePessoa, endereco);
        if(tipoDePessoa != TipoDePessoa.FISICA) {
            throw new IllegalArgumentException("Tipo de pessoa inválida");
        }

        
        this.nome = nome;
        this.cpf = cpf;
        
        System.out.println("Cliente cadastrado com sucesso!");
    }

    @Override
    public void addTelefone(Telefone telefone) {
        if(telefones.size() >= 3) {
            throw new IllegalArgumentException("O cliente não pode ter mais de 3 telefones");
        }
        telefones.add(telefone);
        
    }

    

    public CPF getCpf() {
        return cpf;
    }

    public void setCpf(CPF cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
