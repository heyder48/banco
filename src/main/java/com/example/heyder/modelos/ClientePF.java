package com.example.heyder.modelos;


/**
 * Classe que representa um Cliente Pessoa Física
 * @author Heyder
 * 
 */
public class ClientePF extends Cliente {

    private CPF cpf;
    private String nome;
    

    /**
     * Construtor da classe ClientePF
     * Não permite tipo de pessoa inválido
     * @param nome
     * @param cpf
     * @param email
     * @param endereco
     * @param tipoDePessoa
     * 
     */
     

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

    /**
     * Método que adiciona um telefone a lista de telefones do cliente limitanto a quantidade de telefones a 3.
     * @param telefone
     */
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
