package com.example.heyder.modelos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ContaPoupanca extends Conta {

    private BigDecimal rendimento;
    private LocalDate aniversarioConta;

    public ContaPoupanca(String numero, String agencia, Cliente cliente) {
        super(numero, agencia, cliente);
        if(cliente.tipoDePessoa == TipoDePessoa.JURIDICA){
            throw new IllegalArgumentException("Conta poupança não pode ser para pessoa jurídica");
    }

    this.aniversarioConta = LocalDate.now();
    this.rendimento = new BigDecimal(0);
    

    
    
}

    @Override
    public void sacar(BigDecimal valor) {
        if(valor.compareTo(super.saldo) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        super.saldo.subtract(valor);
        
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

        this.sacar(valor);
        conta.depositar(valor);
        
        
    }

    public void atualizarAniversario(LocalDate aniversarioConta) {
        this.aniversarioConta = aniversarioConta;
    }

    public void atualizaRendimento(LocalDate dataAtual){
        //calular o rendimento quando a data atual for um mes depois do aniversario da conta
        if(ChronoUnit.MONTHS.between(aniversarioConta, dataAtual) == 1){
            super.saldo.add(super.saldo.multiply(rendimento.divide(new BigDecimal(100)).add(new BigDecimal(1))));
            this.atualizarAniversario(aniversarioConta.plusMonths(1));
        }
    }

}
