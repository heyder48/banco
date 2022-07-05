package com.example.heyder.modelos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ContaInvestimento extends Conta {

    private final BigDecimal taxa = new BigDecimal(2.5).divide(new BigDecimal(100));
    private BigDecimal saldoInvestimento;
    private BigDecimal rendimento ;
    private LocalDate dataInicial;


    public ContaInvestimento(String numero, String agencia, Cliente cliente, BigDecimal saldoInvestimento) {
        super(numero, agencia, cliente);
        this.saldoInvestimento = saldoInvestimento;
        this.dataInicial = LocalDate.now();
        this.rendimento = new BigDecimal(0);
        
    }

    @Override
    public void sacar(BigDecimal valor) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void depositar(BigDecimal valor) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void transferir(BigDecimal valor, Conta conta) {
        // TODO Auto-generated method stub
        
    }

    public BigDecimal getSaldoInvestimento() {
        return saldoInvestimento;
    }

    public void atualizaRendimento(LocalDate dataAtual) {
        if(ChronoUnit.YEARS.between(dataInicial, dataAtual) == 1) {

            if(super.cliente.tipoDePessoa==TipoDePessoa.JURIDICA){
                this.rendimento = this.saldoInvestimento.multiply(taxa.add(new BigDecimal(2).divide(new BigDecimal(100))));
                this.saldoInvestimento = this.saldoInvestimento.add(this.rendimento);
            }
            rendimento = saldoInvestimento.multiply(taxa);
            saldoInvestimento = saldoInvestimento.add(rendimento);
            
        }
        
    }
    
}
