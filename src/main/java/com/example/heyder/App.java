package com.example.heyder;

import java.util.Scanner;

import com.example.heyder.modelos.CNPJ;
import com.example.heyder.modelos.CPF;
import com.example.heyder.modelos.Cliente;
import com.example.heyder.modelos.ClientePF;
import com.example.heyder.modelos.ClientePJ;
import com.example.heyder.modelos.ContaCorrente;
import com.example.heyder.modelos.ContaPoupanca;
import com.example.heyder.modelos.Email;
import com.example.heyder.modelos.Endereco;
import com.example.heyder.modelos.Telefone;
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
    public static void main( String[] args )
    {
        System.out.println( "Bem Vindo ao Banco!" );

        System.out.println("\n\n\n");

        System.out.println("Vamos começar cadastrando um cliente?");
        System.out.println("\n\n\n");

        System.out.println("Primeiro, qual é o tipo de pessoa que deseja cadastrar?");
        System.out.println("1 - Pessoa Física");
        System.out.println("2 - Pessoa Jurídica");
        Scanner scanner = new Scanner(System.in);

        Cliente cliente = null;

        switch (scanner.nextInt()) {
            case 1:
                cliente =  cadastrarPessoaFisica();
                break;
            case 2:
                cliente = cadastrarPessoaJuridica();
                break;
        
            default:
                System.out.println("Opção inválida");
                break;
        }

    }

    public static ClientePF cadastrarPessoaFisica() {

        Scanner scanner = new Scanner(System.in);
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
            return clientePF;
            
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }finally {
            scanner.close();
        }

        return null;
        
    }

    public static ClientePJ cadastrarPessoaJuridica() {

        Scanner scanner = new Scanner(System.in);
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
            ClientePJ cleintePJ = new ClientePJ(new Email(email), TipoDePessoa.JURIDICA, new Endereco(logradouro, numero, null, bairro, cidade, uf, cep), new CNPJ(cnpj), razaoSocial, nomeFantasia, new Titular(nomeTitular, new Email(emailTitular), new CPF(cpfTitular)));
            cleintePJ.addTelefone(new Telefone(ddd, numeroTelefone));
            return cleintePJ;
         }catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }finally {
            scanner.close();
        }

        return null;

       
    }

    public static ContaCorrente cadastrarCC(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número da conta:");
        String numero = scanner.nextLine();
        System.out.println("Digite a agência:");
        String agencia = scanner.nextLine();

        try {
            ContaCorrente contaCorrente = new ContaCorrente(agencia, numero, cliente);
            return contaCorrente;
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar conta: " + e.getMessage());
        }finally {
            scanner.close();
        }
        return null;
    }

    public static ContaPoupanca cadastrarCP(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número da conta:");
        String numero = scanner.nextLine();
        System.out.println("Digite a agência:");
        String agencia = scanner.nextLine();

        try {
            ContaPoupanca contaPoupanca = new ContaPoupanca(agencia, numero, cliente);
            return contaPoupanca;
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar conta: " + e.getMessage());
        }finally{
            scanner.close();
        }
        return null;
    }


}
