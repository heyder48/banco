package com.example.heyder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.heyder.modelos.Banco;
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
import com.example.heyder.modelos.Telefone;
import com.example.heyder.modelos.TipoDeConta;
import com.example.heyder.modelos.TipoDePessoa;
import com.example.heyder.modelos.Titular;

/**
 * Applicação que simula o funcionamento de um banco.
 * 
 * @author Heyder
 * @version 1.0
 * @since 1.0
 * 
*/

public class App 
{
    private final static Scanner scanner = new Scanner(System.in);
    public static void main( String[] args )
    {
        System.out.println( "Bem Vindo ao Banco!" );

        System.out.println("\n\n\n");

        System.out.println("Vamos começar cadastrando um cliente?");
        System.out.println("\n\n\n");

        System.out.println("Primeiro, qual é o tipo de pessoa que deseja cadastrar?");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        

        Cliente cliente = null;
        Banco banco = new Banco();

        switch (scanner.nextInt()) {
            case 1:
                cliente =  cadastrarPessoaFisica(banco);
                break;
            case 2:
                cliente = cadastrarPessoaJuridica(banco);
                break;
        
            default:
                System.out.println("Opção inválida");
                break;
        }

        while(true){

            //lista operações disponíveis
            System.out.println("\n\n\n");
            System.out.println("Vamos começar a realizar uma operação?");
            System.out.println("\n\n\n");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Listar contas");
            System.out.println("6 - Saldo");
            System.out.println("7 - Investimentos");
            System.out.println("8 - Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Qual o tipo de conta que deseja criar?");
                    
                    TipoDeConta tipo = null;
                    if(cliente instanceof ClientePF){
                        System.out.println("1 - Conta Corrente");
                        System.out.println("2 - Conta Poupança");
                        System.out.println("3 - Conta Investimento");

                        switch (scanner.nextInt()) {
                            case 1:
                                tipo = TipoDeConta.CORRENTE;
                                break;
                            case 2:
                                tipo = TipoDeConta.POUPANCA;
                                break;
                            case 3:
                                tipo = TipoDeConta.INVESTIMENTO;
                                break;
                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                    }else if(cliente instanceof ClientePJ){
                        System.out.println("1 - Conta Corrente");
                        System.out.println("2 - Conta Investimento");
                        
                        switch (scanner.nextInt()) {
                            case 1:
                                tipo = TipoDeConta.CORRENTE;
                                break;
                            case 2:
                                tipo = TipoDeConta.INVESTIMENTO;
                                break;
                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                    }
                    
                    criarConta(cliente, tipo, banco);
                    break;
                case 2:
                    System.out.println("Qual o valor do depósito?");
                    BigDecimal valor = scanner.nextBigDecimal();
                    depositar(cliente,valor);
                    break;
                case 3:
                    System.out.println("Qual o valor do saque?");
                    BigDecimal valorSaque = scanner.nextBigDecimal();
                    sacar(cliente,valorSaque);
                    break;
                case 4:
                    
                    System.out.println("Qual o valor da transferência?");
                    BigDecimal valorTransferencia = scanner.nextBigDecimal();
                    transferir(cliente,valorTransferencia,banco);
                    break;
                case 5:
                    listarContas(cliente);
                    break;
                case 6:
                    saldo(cliente);
                    break;
                case 7:
                    investimentos(cliente);
                    break;
                case 8:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }


        }

    }

    private static void investimentos(Cliente cliente) {

        Conta selecionada = null;
        

        if(cliente instanceof ClientePF){
            List<Integer> indiceContasSelecionaveis = new ArrayList<>();
            for(int i = 0; i < cliente.getContas().size(); i++){
                if(cliente.getContas().get(i) instanceof ContaInvestimento || cliente.getContas().get(i) instanceof ContaPoupanca){
                    System.out.println("Conta " + (i+1) + ": " + cliente.getContas().get(i));
                    indiceContasSelecionaveis.add(i);
                }
            }
            
            boolean flag = false;
            int opcao;
            while(!flag){

                opcao = scanner.nextInt();
                //se opcao existir em indiceContasSelecionaveis, seleciona a conta
                if(opcao > 0 && indiceContasSelecionaveis.contains(opcao-1)){
                    
                    flag = true;

            }
            

            try{
                selecionada = cliente.getContas().get(opcao-1);
            }catch(IndexOutOfBoundsException e){
                System.out.println("Opção inválida");
                return;
            }
        }
        }else if(cliente instanceof ClientePJ){
            if(cliente instanceof ClientePF){
                List<Integer> indiceContasSelecionaveis = new ArrayList<>();
                for(int i = 0; i < cliente.getContas().size(); i++){
                    if(cliente.getContas().get(i) instanceof ContaInvestimento){
                        System.out.println("Conta " + (i+1) + ": " + cliente.getContas().get(i));
                        indiceContasSelecionaveis.add(i);
                    }
                }
                
                boolean flag = false;
                int opcao;
                while(!flag){
    
                    opcao = scanner.nextInt();
                    //se opcao existir em indiceContasSelecionaveis, seleciona a conta
                    if(opcao > 0 && indiceContasSelecionaveis.contains(opcao-1)){
                        
                        flag = true;
    
                }
                
    
                try{
                    selecionada = cliente.getContas().get(opcao-1);
                }catch(IndexOutOfBoundsException e){
                    System.out.println("Opção inválida");
                    return;
                }
            }


        }
        }

        System.out.println("Deseja:");
        System.out.println("1 - Aplicar");
        System.out.println("2 - Resgatar");
        System.out.println("3 - ver saldo");
        System.out.println("4 - Voltar");

        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("Qual o valor da aplicação?");
                BigDecimal valor = scanner.nextBigDecimal();
                selecionada.depositar(valor);
                break;
            case 2:
                System.out.println("Qual a data do resgate?[dd/MM/yyyy]");
                String data = scanner.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dataResgate = LocalDate.parse(data, formatter);
                if(selecionada instanceof ContaInvestimento){
                    ((ContaInvestimento) selecionada).atualizaRendimento(dataResgate);
                }else if(selecionada instanceof ContaPoupanca){
                    ((ContaPoupanca) selecionada).atualizaRendimento(dataResgate);
                }

                System.out.println("O rendimento até data de resgate foi: " + (selecionada instanceof ContaInvestimento ? ((ContaInvestimento) selecionada).getRendimento() : ((ContaPoupanca) selecionada).getRendimento()));
                
                System.out.println("Qual o valor do resgate?");
                BigDecimal valorResgate = scanner.nextBigDecimal();
                selecionada.sacar(valorResgate);
                break;
            case 3:
                System.out.println("Saldo: R$ " + selecionada.getSaldo());
                break;
            case 4:
                return;
            default:
                System.out.println("Opção inválida");
                break;
        }


        
    }

    private static void saldo(Cliente cliente) {

        System.out.println("De qual conta deseja ver o saldo?");
        listarContas(cliente);
        
        int conta = scanner.nextInt();
        Conta contaSelecionada = cliente.getContas().get(conta-1);
        System.out.println("O saldo da conta é: R$ " + contaSelecionada.getSaldo());
    }

    private static void transferir(Cliente cliente, BigDecimal valorTransferencia, Banco banco) {

        
        System.out.println("Qual o número da conta que deseja transferir?");
        String numeroConta = scanner.next();
        Conta conta = banco.buscaConta(numeroConta);
        if(conta == null){
            System.out.println("Conta não encontrada");
            return;
        }

        System.out.println("De qual conta você deseja transferir?");
        listarContas(cliente);

        int contaOrigem = scanner.nextInt();

        Conta contaDestino = cliente.getContas().get(contaOrigem -1);

        try {
            contaDestino.transferir(valorTransferencia,conta);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void depositar(Cliente cliente, BigDecimal valor) {
        
        System.out.println("Qual a conta que deseja depositar?");
        listarContas(cliente);
        int conta = scanner.nextInt();
        Conta contaSelecionada = cliente.getContas().get(conta-1);
        contaSelecionada.depositar(valor);
    }

    /**
     * Método para listar as contas de um cliente.
     * @param cliente
     */
    public static void listarContas(Cliente cliente) {

        if(cliente instanceof ClientePF){
            System.out.println("Contas do cliente: " +((ClientePF) cliente).getNome());
            for (int i = 0; i < cliente.getContas().size(); i++) {
                System.out.println("Conta " + (i+1) + ": " + cliente.getContas().get(i));
                System.out.println("\n");
            }
        }else if(cliente instanceof ClientePJ){
            System.out.println("Contas do cliente: " +((ClientePJ) cliente).getNomeFantasia());
            for (int i = 0; i < cliente.getContas().size(); i++) {
                System.out.println("Conta " + (i+1) + ": " + cliente.getContas().get(i));
                System.out.println("\n");
            }
        }

    }

    public static ClientePF cadastrarPessoaFisica(Banco banco) {
        

        
        System.out.println("Digite o nome do cliente:");
        String nome = scanner.nextLine();
        System.out.println("Digite o CPF do cliente:");
        String cpf = scanner.nextLine();
        System.out.println("Digite o email do cliente:");
        String email = scanner.nextLine();
        System.out.println("Digite o CEP do cliente:");
        String cep = scanner.nextLine();
        System.out.println("Digite o logradouro do cliente:");
        String logradouro = scanner.nextLine();
        System.out.println("Digite o número do cliente:");
        String numero = scanner.nextLine();
        System.out.println("Digite o bairro do cliente:");
        String bairro = scanner.nextLine();
        System.out.println("Digite a cidade do cliente:");
        String cidade = scanner.nextLine();
        System.out.println("Digite a UF do cliente:");
        String uf = scanner.nextLine();
        System.out.println("Digite o DDD do cliente:");
        String ddd = scanner.nextLine();
        System.out.println("Digite o número do telefone do cliente:");
        String numeroTelefone = scanner.nextLine();

        try {
            ClientePF clientePF = new ClientePF(nome, new CPF(cpf), new Email(email), TipoDePessoa.FISICA, new Endereco(logradouro, numero,null, bairro, cidade, uf,cep));
            clientePF.addTelefone(new Telefone(ddd, numeroTelefone));
            banco.addClientes(clientePF);

            return clientePF;
            
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }

        return null;
        
    }

    public static ClientePJ cadastrarPessoaJuridica(Banco banco) {

        
        System.out.println("Digite a Razão Social da empresa:");
        String razaoSocial = scanner.nextLine();
        System.out.println("Digite o CNPJ da empresa:");
        String cnpj = scanner.nextLine();
        System.out.println("Digite o Nome Fantasia da empresa:");
        String nomeFantasia = scanner.nextLine();
        System.out.println("Digite o email da empresa:");
        String email = scanner.nextLine();
        System.out.println("Digite o nome do Titular da empresa:");
        String nomeTitular = scanner.nextLine();
        System.out.println("Digite o email do Titular da empresa:");
        String emailTitular = scanner.nextLine();
        System.out.println("Digite o CPF do Titular da empresa:");
        String cpfTitular = scanner.nextLine();
        System.out.println("Digite o CEP  da empresa:");
        String cep = scanner.nextLine();
        System.out.println("Digite o logradouro da empresa:");
        String logradouro = scanner.nextLine();
        System.out.println("Digite o número da empresa:");
        String numero = scanner.nextLine();
        System.out.println("Digite o bairro da empresa:");
        String bairro = scanner.nextLine();
        System.out.println("Digite a cidade da empresa:");
        String cidade = scanner.nextLine();
        System.out.println("Digite a UF da empresa:");
        String uf = scanner.nextLine();
        System.out.println("Digite o DDD do telefone da empresa:");
        String ddd = scanner.nextLine();
        System.out.println("Digite o número do telefone da empresa:");
        String numeroTelefone = scanner.nextLine();

         try{
            ClientePJ clientePJ = new ClientePJ(new Email(email), TipoDePessoa.JURIDICA, new Endereco(logradouro, numero, null, bairro, cidade, uf, cep), new CNPJ(cnpj), razaoSocial, nomeFantasia, new Titular(nomeTitular, new Email(emailTitular), new CPF(cpfTitular)));
            clientePJ.addTelefone(new Telefone(ddd, numeroTelefone));
            banco.addClientes(clientePJ);
            return clientePJ;
         }catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }

        return null;

       
    }

    public static ContaCorrente cadastrarCC(Cliente cliente, String numero, String agencia, Banco banco) {
        

        try {
            ContaCorrente contaCorrente = new ContaCorrente(agencia, numero, cliente);
            cliente.addContas(contaCorrente);
            banco.addContas(contaCorrente);
            return contaCorrente;
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar conta: " + e.getMessage());
        }
        return null;
    }

    public static ContaPoupanca cadastrarCP(Cliente cliente, String numero, String agencia, Banco banco) {
        

        try {
            ContaPoupanca contaPoupanca = new ContaPoupanca(agencia, numero, cliente);
            cliente.addContas(contaPoupanca);
            banco.addContas(contaPoupanca);
            return contaPoupanca;
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar conta: " + e.getMessage());
        }

        return null;
    }

    public static ContaInvestimento cadastrarCI(Cliente cliente, String numero, String agencia, Banco banco) {
        

        try {
            ContaInvestimento contaInvestimento = new ContaInvestimento(agencia, numero, cliente);
            cliente.addContas(contaInvestimento);
            banco.addContas(contaInvestimento);
            return contaInvestimento;
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar conta: " + e.getMessage());
        }
        return null;
    }

    public static Conta criarConta(Cliente cliente, TipoDeConta tipoDeConta, Banco banco) {

        
        System.out.println("Digite o número da conta:");
        String numero = scanner.nextLine();
        System.out.println("Digite a agência:");
        String agencia = scanner.nextLine();

        

        switch(tipoDeConta){
            case CORRENTE:
                return cadastrarCC(cliente, numero, agencia, banco);
            case POUPANCA:
                return cadastrarCP(cliente, numero, agencia, banco);
            case INVESTIMENTO:
                return cadastrarCI(cliente, numero, agencia,banco);
                
            default:
                System.out.println("Tipo de conta inválida");
                return null;
                
        }



    }

    public static void sacar(Cliente cliente, BigDecimal valor ){

        
        System.out.println("Qual a conta que deseja sacar?");
        listarContas(cliente);
        int conta = scanner.nextInt();

        Conta contaSelecionada = cliente.getContas().get(conta-1);

        try {
            contaSelecionada.sacar(valor);
        } catch (Exception e) {
            System.out.println("Erro ao sacar: " + e.getMessage());
        }
    }
}
