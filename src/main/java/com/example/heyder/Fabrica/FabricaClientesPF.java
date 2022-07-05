package com.example.heyder.Fabrica;

import com.example.heyder.modelos.CPF;
import com.example.heyder.modelos.ClientePF;
import com.example.heyder.modelos.Email;
import com.example.heyder.modelos.Endereco;
import com.example.heyder.modelos.Telefone;
import com.example.heyder.modelos.TipoDePessoa;

public class FabricaClientesPF {

    private ClientePF clientePF;

   public FabricaClientesPF semTelefone(String nome, String cpf, String email,TipoDePessoa tipoDePessoa, String logradouro, String numero, String complemento, String bairro, String cidade, String estado, String cep){
     this.clientePF = new ClientePF(nome,
                        new CPF(cpf), 
                        new Email(email), 
                        tipoDePessoa,
                        new Endereco(logradouro, numero, complemento, bairro, cidade, estado, cep));
        return this;
       }
    
       public FabricaClientesPF comTelefone(String ddd, String numero){
              this.clientePF.addTelefone(new Telefone(ddd, numero));
              return this;
       }

    public ClientePF criar(){

        return this.clientePF;
    }
    
}
