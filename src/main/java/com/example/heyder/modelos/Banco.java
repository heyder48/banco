package com.example.heyder.modelos;

import java.util.List;

public class Banco {

    private List<Conta> contas;
    private List<Cliente> clientes;


    //adicionar clientes

    public void addClientes(Cliente cliente){
        clientes.add(cliente);
    }

    //adicionar contas

    public void addContas(Conta conta){
        contas.add(conta);
    }

    //busca conta pelo numero
    public Conta buscaConta(String numero) {
        for (Conta conta : contas) {
            if (conta.getNumero().equals(numero)) {
                return conta;
            }
        }
        return null;
    }

    //busca clientePF pelo cpf ou cnpj
    public Cliente buscaCliente(String cpfCnpj,TipoDePessoa tipoDePessoa) {
        if(tipoDePessoa == TipoDePessoa.FISICA){
            for (Cliente cliente : clientes){
                if(cliente instanceof ClientePF && ((ClientePF)cliente).getCpf().getNumero().equals(cpfCnpj)){
                    return cliente;
                }
            }
        }else if(tipoDePessoa == TipoDePessoa.JURIDICA){
            for (Cliente cliente : clientes){
                if(cliente instanceof ClientePJ && ((ClientePJ)cliente).getCnpj().getNumero().equals(cpfCnpj)){
                    return cliente;
                }
            }
        }

        return null;
    }
    
}
