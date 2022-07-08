package com.example.heyder.modelos;

/**
 * A classe endereco é uma representação de um endereço.
 * @author heyder
 */
 
public class Endereco {
         
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    /**
     * Construtor da classe Endereco
     * Valida se o endereço é não nulo.
     * @param logradouro
     * @param numero
     * @param complemento
     * @param bairro
     * @param cidade
     * @param estado
     * @param cep
     */
    public Endereco(String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String cep) {
        if (logradouro == null || numero == null ||  bairro == null || cidade == null || estado == null || cep == null) {
            throw new IllegalArgumentException("Endereco invalido");
        }
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    
    /** 
     * @return String
     */
    public String getCep() {
        return cep;
    }

    
    /** 
     * @param cep
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    
    /** 
     * @return String
     */
    public String getEstado() {
        return estado;
    }

    
    /** 
     * @param estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    /** 
     * @return String
     */
    public String getLogradouro() {
        return logradouro;
    }
    
    
    /** 
     * @param logradouro
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
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
    
    
    /** 
     * @return String
     */
    public String getComplemento() {
        return complemento;
    }
    
    
    /** 
     * @param complemento
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    
    
    /** 
     * @return String
     */
    public String getBairro() {
        return bairro;
    }
    
    
    /** 
     * @param bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    
    /** 
     * @return String
     */
    public String getCidade() {
        return cidade;
    }
    
    
    /** 
     * @param cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

}
