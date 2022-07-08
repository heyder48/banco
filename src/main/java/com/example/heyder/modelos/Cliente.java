package com.example.heyder.modelos;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe Cliente é uma representação abstrata de um cliente do banco.
 * @author heyder
 */

public abstract class Cliente {

    protected String nome;
    protected Email email;
    protected List<Telefone> telefones = new ArrayList<>();
    
    protected TipoDePessoa tipoDePessoa;
    protected Endereco endereco;

    public Cliente(Email email,  TipoDePessoa tipoDePessoa, Endereco endereco) {
        this.email = email;
        this.tipoDePessoa = tipoDePessoa;
        this.endereco = endereco;
    }

    /**
     * Método que adiciona um telefone a lista de telefones do cliente.
     * @param telefone
     * @See Telefone
     */
    public abstract void addTelefone(Telefone telefone);    
    

    /**
     * Método para deletar um telefone da lista de telefones do cliente.
     * @param telefone
     * @See Telefone
     *
     */
    public  void deleteTelefone(Telefone telefone){
        telefones.remove(telefone);
    }

    /**
     * Método que retorna o tipo de pessoa do cliente.
     * @return TipoDePessoa
     * @See TipoDePessoa
     */
    public TipoDePessoa getTipoDePessoa(){
        return tipoDePessoa;
    }
        
    



    
}
