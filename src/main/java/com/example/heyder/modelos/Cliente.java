package com.example.heyder.modelos;

import java.util.ArrayList;
import java.util.List;

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

    public abstract void addTelefone(Telefone telefone);    
    

    //deleta o telefone passado como parametro
    public  void deleteTelefone(Telefone telefone){
        telefones.remove(telefone);
    }

    public TipoDePessoa getTipoDePessoa(){
        return tipoDePessoa;
    }
        
    



    
}
