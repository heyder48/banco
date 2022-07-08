package com.example.heyder.modelos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe que representa uma conta investimento
 * Herda de Conta
 * @author Heyder
 */
public class ContaInvestimento extends Conta {

    /**
     *Atributo taxa representa a taxa de rendimento da conta
     *
     */

    private final BigDecimal taxa = new BigDecimal(2.5);
    
    /**
     * Atributo rendimento representa o rendimento da conta
     */
    private BigDecimal rendimento ;

    /**
     * Atributo dataInicial representa a data inicial da conta
     *
     */
    private LocalDate dataInicial;

    /**
     * Construtor da classe ContaInvestimento
     * @param numero
     * @param agencia
     * @param cliente
     * @param saldoInvestimento
     */
    public ContaInvestimento(String numero, String agencia, Cliente cliente, BigDecimal saldoInvestimento) {
        super(numero, agencia, cliente);
        this.dataInicial = LocalDate.now();
        this.rendimento = new BigDecimal(0);
        super.tipo = TipoDeConta.INVESTIMENTO;
        System.out.println("Conta investimento criada com sucesso!");
        
    }

    /**
     * Método para consultar o rendimento da conta
     * @return rendimento
     */
      
    public BigDecimal getRendimento() {
        return rendimento.setScale(2,RoundingMode.HALF_UP);
    }

   

    
    /**
     * Método para atualizar o rendimento da conta.
     * O calculo é feito utilizando juros compostos com a taxa de rendimento anual tranformada em diária.
     * Caso o cliente seja pessoa juridica a taxa de rendimento é adicionada de 2%.
     * A data de início da conta é atualizada para a data atual.
     * @param dataAtual
     *
     */
    public void atualizaRendimento(LocalDate dataAtual) {

        if(ChronoUnit.DAYS.between(dataInicial, dataAtual) < 0) {
            throw new IllegalArgumentException("Data inválida");
            
        }

        Long tempo = ChronoUnit.DAYS.between(dataInicial, dataAtual);

        BigDecimal taxaDiaria = new BigDecimal(0);

        if(cliente.tipoDePessoa == TipoDePessoa.JURIDICA){
            taxaDiaria = taxaAnualParaDiaria(taxa.add(new BigDecimal(2)));
        }else{
            taxaDiaria = taxaAnualParaDiaria(taxa);
        }

        //Juros compostos
        rendimento = new BigDecimal(Math.pow(1 + taxaDiaria.divide(new BigDecimal(100)).doubleValue(), tempo.doubleValue()) * saldo.doubleValue()).subtract(saldo);
        saldo = new BigDecimal(Math.pow(1 + taxaDiaria.divide(new BigDecimal(100)).doubleValue(), tempo.doubleValue()) * saldo.doubleValue());

        dataInicial = dataAtual;

        
    }

    /**
     * Método para converter uma taxa anual em uma taxa diária
     * @param taxaAnual
     * @return taxaDiaria
     *
     */

    private BigDecimal taxaAnualParaDiaria(BigDecimal taxaAnual){

        return  new BigDecimal(Math.pow(1+taxaAnual.doubleValue(), 1/365)-1);
    }
    
}
