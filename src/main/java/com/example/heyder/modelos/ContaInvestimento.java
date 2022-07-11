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

    private BigDecimal saldo;

    /**
     * Construtor da classe ContaInvestimento
     * @param numero
     * @param agencia
     * @param cliente
     * 
     */
    public ContaInvestimento(String numero, String agencia, Cliente cliente) {
        super(numero, agencia, cliente);
        this.dataInicial = LocalDate.now();
        this.rendimento = new BigDecimal(0);
        this.saldo = BigDecimal.ZERO;
        super.tipo = TipoDeConta.INVESTIMENTO;
        System.out.println("Conta investimento criada com sucesso!");
        
    }

    @Override
    public void depositar(BigDecimal valor) {
        if(valor.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Valor inválido");
        }
        
        this.saldo = this.saldo.add(valor);
    }

    @Override
    public BigDecimal getSaldo() {
        return this.saldo.setScale(2, RoundingMode.HALF_EVEN);
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

        double taxaDiaria = BigDecimal.ZERO.doubleValue();

        if(cliente.tipoDePessoa == TipoDePessoa.JURIDICA){
            taxaDiaria = taxaAnualParaDiaria(taxa.add(new BigDecimal(2)),tempo);
        }else{
            taxaDiaria = taxaAnualParaDiaria(taxa,tempo);
        }

        //Juros compostos
        this.rendimento = new BigDecimal(Math.pow((1 + taxaDiaria/100), tempo.doubleValue()) * this.saldo.doubleValue()).subtract(this.saldo);
        this.saldo = new BigDecimal(Math.pow((1 + taxaDiaria/100), tempo.doubleValue()) * this.saldo.doubleValue());

        dataInicial = dataAtual;

        
    }

    /**
     * Método para converter uma taxa anual em uma taxa diária
     * @param taxaAnual
     * @return taxaDiaria
     *
     */

    private Double taxaAnualParaDiaria(BigDecimal taxaAnual, Long tempo){

        double taxaAnulaD = taxaAnual.doubleValue();

        double taxaDiaria = Math.pow(1+taxaAnulaD/100, (double)tempo/360.00);

        return  taxaDiaria-1;
    }
    
}
