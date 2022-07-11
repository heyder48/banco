package com.example.heyder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.heyder.modelos.Banco;
import com.example.heyder.modelos.CNPJ;
import com.example.heyder.modelos.CPF;
import com.example.heyder.modelos.Cliente;
import com.example.heyder.modelos.ClientePF;
import com.example.heyder.modelos.ClientePJ;
import com.example.heyder.modelos.Conta;
import com.example.heyder.modelos.ContaCorrente;
import com.example.heyder.modelos.Email;
import com.example.heyder.modelos.Endereco;
import com.example.heyder.modelos.TipoDePessoa;
import com.example.heyder.modelos.Titular;

public class BancoTeste {

    private Banco banco;
    private Cliente clientePF;
    private Cliente clientePJ;
    private Conta contaPF;
    private Conta contaPJ;

    @BeforeEach
    public void setUp() {
        this.banco = new Banco();
        this.clientePF = new ClientePF("João",
                                        new CPF("12345678901"),
                                        new Email("teste@teste.com"),
                                        TipoDePessoa.FISICA,
                                        new Endereco("Rua Teste", "123",null, "Teste", "Teste", "Teste", "Teste"));
        
        this.clientePJ = new ClientePJ(new Email("teste@teste.com"),
                                          TipoDePessoa.JURIDICA,
                                          new Endereco("Rua", "123",null, "Bairro", "Cidade", "Estado", "CEP"),
                                          new CNPJ("12345678911011"),
                             "Razão Social",
                                "Nome Fantasia",
                                          new Titular("Cleber", new Email("cleber@teste.com"), new CPF("12345678901")));
        
        this.contaPF = new ContaCorrente("123567", "12345-6", clientePF);
        this.contaPJ = new ContaCorrente("123568", "12345-6", clientePJ);

    }

    @Test
    public void testeCadastrarCliente() {
        banco.addClientes(clientePF);
        banco.addClientes(clientePJ);
        assertEquals(2, banco.getClientes().size());
    }

    @Test
    public void testeCadastrarConta() {
        banco.addContas(contaPF);
        banco.addContas(contaPJ);
        assertEquals(2, banco.getContas().size());
    }

    @Test
    public void testeBuscaConta() {
        banco.addContas(contaPF);
        banco.addContas(contaPJ);
        assertEquals(contaPF, banco.buscaConta("123567"));
    }

    @Test
    public void testeBuscaCliente() {
        banco.addClientes(clientePF);
        banco.addClientes(clientePJ);
        assertEquals(clientePF, banco.buscaCliente("12345678901", TipoDePessoa.FISICA));
    }

    @Test
    public void testeBuscaClientePJ() {
        banco.addClientes(clientePF);
        banco.addClientes(clientePJ);
        assertEquals(clientePJ, banco.buscaCliente("12345678911011", TipoDePessoa.JURIDICA));
    }

    @Test
    public void contaNaoEncontrada(){
        banco.addContas(contaPF);
        banco.addContas(contaPJ);
        assertEquals(null, banco.buscaConta("1235"));
    }

    @Test
    public void clienteNaoEncontrado(){
        banco.addClientes(clientePF);
        banco.addClientes(clientePJ);
        assertEquals(null, banco.buscaCliente("123456789", TipoDePessoa.FISICA));
    }
    
}
