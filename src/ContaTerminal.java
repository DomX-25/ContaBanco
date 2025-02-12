
import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        double saldo = 0.0;

        System.out.println("Conta criada com sucesso!");
        System.out.println("Saldo atual: R$ " + saldo);

        // Depósito
        System.out.print("Digite o valor para depósito: R$ ");
        double deposito = scanner.nextDouble();
        saldo += deposito;
        System.out.println("Depósito realizado com sucesso!");
        System.out.println("Saldo atualizado: R$ " + saldo);

        // Saque
        System.out.print("Digite o valor para saque: R$ ");
        double saque = scanner.nextDouble();

        if (saque > saldo) {
            System.out.println("Saldo insuficiente para saque.");
        } else {
            saldo -= saque;
            System.out.println("Saque realizado com sucesso!");
            System.out.println("Saldo atualizado: R$ " + saldo);
        }

        scanner.close();
        


        boolean modoTeste = true; // Altere para false para entrada manual
        
        scanner = configurarScanner(modoTeste);
        
        try {
            // Dados que serão preenchidos
            int numeroConta;
            String numeroAgencia;
            String nomeCliente;
            double saldoConta;

            if(modoTeste) {
                System.out.println("=== MODO DE TESTE ATIVADO ===");
                numeroConta = 12345;
                numeroAgencia = "0678-9";
                nomeCliente = "Maria Oliveira Souza";
                saldoConta = 2550.75;
                
                exibirDadosSimulados();
            } else {
                numeroConta = obterNumeroConta(scanner);
                numeroAgencia = obterAgencia(scanner);
                nomeCliente = obterNomeCliente(scanner);
                saldoConta = obterSaldo(scanner);
            }

            exibirResumoConta(nomeCliente, numeroAgencia, numeroConta, saldoConta);

        } finally {
            scanner.close();
        }
    }

    private static Scanner configurarScanner(boolean modoTeste) {
        if(modoTeste) {
            String dadosSimulados = "12345\n0678-9\nMaria Oliveira Souza\n2550.75\n";
            return new Scanner(dadosSimulados);
        }
        return new Scanner(System.in);
    }

    private static void exibirDadosSimulados() {
        System.out.println("\nDados de Exemplo Carregados:");
        System.out.println("Número da conta: 12345");
        System.out.println("Agência: 0678-9");
        System.out.println("Nome: Maria Oliveira Souza");
        System.out.println("Saldo: 2550.75\n");
    }

    // Métodos de validação mantidos iguais ao código anterior
    private static int obterNumeroConta(Scanner scanner) {
        System.out.println("Digite o número da conta:");
        int numero = scanner.nextInt();
        scanner.nextLine();
        if(numero <= 0) throw new IllegalArgumentException("Número inválido!");
        return numero;
    }

    private static String obterAgencia(Scanner scanner) {
        System.out.println("Digite a agência (XXXX-X):");
        String agencia = scanner.nextLine();
        if(!agencia.matches("\\d{4}-\\d")) throw new IllegalArgumentException("Formato inválido!");
        return agencia;
    }

    private static String obterNomeCliente(Scanner scanner) {
        System.out.println("Digite o nome completo:");
        String nome = scanner.nextLine().trim();
        if(nome.split(" ").length < 2) throw new IllegalArgumentException("Nome incompleto!");
        return nome;
    }

    private static double obterSaldo(Scanner scanner) {
        System.out.println("Digite o saldo inicial:");
        double saldo = scanner.nextDouble();
        if(saldo < 0) throw new IllegalArgumentException("Saldo negativo!");
        return saldo;
    }

    private static void exibirResumoConta(String nome, String agencia, int conta, double saldo) {
        System.out.println("\n═ RESULTADO ═══════════════════════════");
        System.out.printf("│ %-15s %-25s │\n", "Cliente:", nome);
        System.out.printf("│ %-15s %-25s │\n", "Agência:", agencia);
        System.out.printf("│ %-15s %-25d │\n", "Conta:", conta);
        System.out.printf("│ %-15s R$ %-20.2f │\n", "Saldo:", saldo);
        System.out.println("═════════════════════════════════════════");
    }
}