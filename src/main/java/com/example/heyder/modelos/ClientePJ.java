package com.example.heyder.modelos;

/**
 * A classe ClientePJ é uma representação do cliente pessoa juridica.
 * @author heyder
 */

public class ClientePJ extends Cliente {

    private CNPJ cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private Titular titular;

    /**
     * Construtor da classe ClientePJ
     * Não permite tipo de pessoa inválido
     * @param nomeFantasia
     * @param razaoSocial
     * @param cnpj
     * @param email
     * @param endereco
     * @param tipoDePessoa
     * @param titular
     */
     

    public ClientePJ(Email email, TipoDePessoa tipoDePessoa,
            Endereco endereco, CNPJ cnpj, String razaoSocial, String nomeFantasia, Titular titular) {
        super(email,tipoDePessoa, endereco);
        if (tipoDePessoa != TipoDePessoa.JURIDICA) {
            throw new IllegalArgumentException("Tipo de pessoa inválida");
        }
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.titular = titular;

        System.out.println("Cliente cadastrado com sucesso!");

    }
    
    /**
     * Método que adiciona um telefone a lista de telefones do cliente.
     * @param telefone
     */
     
    @Override
    public void addTelefone(Telefone telefone) {
        telefones.add(telefone);
        
    }
    public CNPJ getCnpj() {
        return cnpj;
    }

    public void setCnpj(CNPJ cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }
    
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getTitular() {
        return titular.getNome();
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    


}
