package com.example.heyder.modelos;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {

    

    public ContaCorrente(String numero, String agencia, Cliente cliente) {
        super(numero, agencia, cliente);
        super.tipo = TipoDeConta.CORRENTE;
        System.out.println("Conta corrente criada com sucesso!");
    }

    @Override
    public void sacar(BigDecimal valor) {
        if(valor.compareTo(saldo) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        if(super.cliente.tipoDePessoa == TipoDePessoa.JURIDICA){
            super.saldo.subtract(valor.multiply(new BigDecimal(1.005)));
        }
        else {
            super.saldo.subtract(valor);
        }
        
    }

    @Override
    public void depositar(BigDecimal valor) {
        super.saldo.add(valor);
        
    }

    @Override
    public void transferir(BigDecimal valor, Conta conta) {
        if(valor.compareTo(super.saldo) > 0){
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        if(super.cliente.tipoDePessoa == TipoDePessoa.JURIDICA){
            this.sacar(valor.multiply(new BigDecimal(1.005)));
            conta.depositar(valor);
        }else{
            this.sacar(valor);
            conta.depositar(valor);
        }
        
    }
    
}
