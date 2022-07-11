package com.example.heyder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.heyder.modelos.CNPJ;
import com.example.heyder.modelos.CPF;
import com.example.heyder.modelos.Cliente;
import com.example.heyder.modelos.ClientePF;
import com.example.heyder.modelos.ClientePJ;
import com.example.heyder.modelos.Conta;
import com.example.heyder.modelos.ContaCorrente;
import com.example.heyder.modelos.ContaInvestimento;
import com.example.heyder.modelos.ContaPoupanca;
import com.example.heyder.modelos.Email;
import com.example.heyder.modelos.Endereco;
import com.example.heyder.modelos.TipoDePessoa;
import com.example.heyder.modelos.Titular;


public class ContaTeste {
    
    //instancia de Conta
    private Conta contaCorrentePF;
    private Conta contaCorrentePJ;
    private Conta contaPoupanca;
    private Conta contaInvestimento;
    @BeforeEach
    public void setUp() {
       this.contaCorrentePF = new ContaCorrente("12345-6",
                                              "12345-6", 
                                              new ClientePF("João", 
                                                            new CPF("12345678901"), 
                                                            new Email("teste@teste.com"),
                                                            TipoDePessoa.FISICA,
                                                            new Endereco("Rua", "123",null, "Bairro", "Cidade", "Estado", "CEP")));
        
        this.contaCorrentePJ = new ContaCorrente("12345-6",
                                                        "12345-6",
                                                        new ClientePJ(new Email("teste@teste.com"),
                                                        TipoDePessoa.JURIDICA,
                                                        new Endereco("Rua", "123",null, "Bairro", "Cidade", "Estado", "CEP"),
                                                        new CNPJ("12345678911011"),
                                                        "Razão Social",
                                                        "Nome Fantasia",
                                                        new Titular("Cleber", new Email("cleber@teste.com"), new CPF("12345678901"))));

        this.contaPoupanca = new ContaPoupanca("12345-6",
                                              "12345-6", 
                                              new ClientePF("João", 
                                                            new CPF("12345678901"), 
                                                            new Email("teste@teste"),
                                                            TipoDePessoa.FISICA,
                                                            new Endereco("Rua", "123",null, "Bairro", "Cidade", "Estado", "CEP")));
        
        this.contaInvestimento = new ContaInvestimento("12345-6",
                                              "12345-6", 
                                              new ClientePF("João", 
                                                            new CPF("12345678901"), 
                                                            new Email("teste@teste"),
                                                            TipoDePessoa.FISICA,
                                                            new Endereco("Rua", "123",null, "Bairro", "Cidade", "Estado", "CEP")));


    }

    @Test
    void naoDeveCriarContaComNumeroInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new ContaCorrente("", "12345-6", new ClientePF("João", 
                                                                                                          new CPF("12345678901"), 
                                                                                                          new Email("teste@teste.com.br"),
                                                                                                          TipoDePessoa.FISICA,
                                                                                                          new Endereco("Rua", "123",null, "Bairro", "Cidade", "Estado", "CEP"))));
    }
    
    @Test
    void naoDeveriaCriarContaComAgenciaInvalida(){
        assertThrows(IllegalArgumentException.class, () -> new ContaCorrente("123456", "", new ClientePF("João", 
                                                                                                          new CPF("12345678901"), 
                                                                                                          new Email("teste@teste.com"),
                                                                                                          TipoDePessoa.FISICA,
                                                                                                          new Endereco("Rua", "123",null, "Bairro", "Cidade", "Estado", "CEP"))));
    }

    @Test
    void naoDeveriaCriarContaComClienteInvalido(){
        assertThrows(IllegalArgumentException.class, () -> new ContaCorrente("123456", "12345-6", null));
    }

    @Test
    void naoDeveriaCriarContaPoupancaComTipoDePessoaJuridica(){
        assertThrows(IllegalArgumentException.class, () -> new ContaPoupanca("123456", "12345-6", new ClientePF("João", 
                                                                                                          new CPF("12345678901"), 
                                                                                                          new Email("teste@teste.com"),
                                                                                                            TipoDePessoa.JURIDICA,
                                                                                                            new Endereco("Rua", "123",null, "Bairro", "Cidade", "Estado", "CEP"))));
    }

    @Test
    void naoDeveriaPermitirDepositarValorNegativo(){
        
        assertThrows(IllegalArgumentException.class, () -> contaCorrentePF.depositar(new BigDecimal("-100")));
    }

    @Test
    void naoDeveriaPermitirSacarValorNegativo(){
        contaCorrentePF.depositar(new BigDecimal("100"));
        assertThrows(IllegalArgumentException.class, () -> contaCorrentePF.sacar(new BigDecimal("-100")));
    }

    @Test
    void naoDeveriaPermitirSacarValorMaiorQueSaldoContaCorrentePF(){
        contaCorrentePF.depositar(new BigDecimal("100"));
        assertThrows(IllegalArgumentException.class, () -> contaCorrentePF.sacar(new BigDecimal("200")));
    }

    @Test

    void naoDeveriaPermitirSacarValorMaiorQueSaldoContaCorrentePJ(){
        contaCorrentePJ.depositar(new BigDecimal("100"));
        //levando em conta a taxa de saque da conta corrente pj
        assertThrows(IllegalArgumentException.class, () -> contaCorrentePJ.sacar(new BigDecimal("99.60")));
        assertThrows(IllegalArgumentException.class, () -> contaCorrentePJ.sacar(new BigDecimal("200")));
    }

    @Test
    void naoDeveriaPermitirSacarValorMaiorQueSaldoContaPoupanca(){
        contaPoupanca.depositar(new BigDecimal("100"));
        assertThrows(IllegalArgumentException.class, () -> contaPoupanca.sacar(new BigDecimal("200")));
    }

    @Test
    void naoDeveriaPermitirTransferirValorNegativo(){
        contaCorrentePF.depositar(new BigDecimal("100"));
        assertThrows(IllegalArgumentException.class, () -> contaCorrentePF.transferir(new BigDecimal("-100"), contaCorrentePJ));
    }

    @Test
    void naoDeveriaPermitirTransferirValorMaiorQueSaldoContaCorrentePF(){
        contaCorrentePF.depositar(new BigDecimal("100"));
        assertThrows(IllegalArgumentException.class, () -> contaCorrentePF.transferir(new BigDecimal("200"), contaCorrentePJ));
    }

    @Test
    void naoDeveriaPermitirTransferirSemContaDestino(){
        contaCorrentePF.depositar(new BigDecimal("100"));
        assertThrows(IllegalArgumentException.class, () -> contaCorrentePF.transferir(new BigDecimal("100"), null));
    }

    //Testes de investimento (conta investimento)

    @Test
    void atualizaRendimentos(){
        contaInvestimento.depositar(new BigDecimal("1000"));
        ((ContaInvestimento) contaInvestimento).atualizaRendimento(LocalDate.now().plusMonths(2));
        assertEquals(new BigDecimal("1002.65"), contaInvestimento.getSaldo());
    }



    





}
