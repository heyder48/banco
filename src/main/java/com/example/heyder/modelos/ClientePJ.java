package com.example.heyder.modelos;



public class ClientePJ extends Cliente {

    private CNPJ cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private Titular titular;

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

    }
    

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
