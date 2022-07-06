package com.example.heyder.modelos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ContaInvestimento extends Conta {

    private final BigDecimal taxa = new BigDecimal(2.5).divide(new BigDecimal(100));
    
    private BigDecimal rendimento ;
    private LocalDate dataInicial;


    public ContaInvestimento(String numero, String agencia, Cliente cliente, BigDecimal saldoInvestimento) {
        super(numero, agencia, cliente);
        this.dataInicial = LocalDate.now();
        this.rendimento = new BigDecimal(0);
        
    }

    @Override
    public void sacar(BigDecimal valor) {
        if (valor.compareTo(saldo) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        saldo = saldo.subtract(valor);
        
    }

    @Override
    public void depositar(BigDecimal valor) {
        saldo = saldo.add(valor);
        
    }

    @Override
    public void transferir(BigDecimal valor, Conta conta) {
        if (valor.compareTo(saldo) > 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        this.sacar(valor);
        conta.depositar(valor);
        
        
    }

    public BigDecimal getSaldoInvestimento() {
        return saldo;
    }

    public void atualizaRendimento(LocalDate dataAtual) {
        if(ChronoUnit.YEARS.between(dataInicial, dataAtual) == 1) {

            if(super.cliente.tipoDePessoa==TipoDePessoa.JURIDICA){
                this.rendimento = this.saldo.multiply(taxa.add(new BigDecimal(2).divide(new BigDecimal(100))));
                this.saldo = this.saldo.add(this.rendimento);
            }
            rendimento = saldo.multiply(taxa);
            saldo = saldo.add(rendimento);
            
        }
        
    }
    
}
