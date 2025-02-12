import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args) throws Exception {
        boolean modoTeste = true; // Altere para false para entrada manual

        Scanner scanner = null;
        try {
            scanner = configurarScanner(modoTeste);

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
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public static Scanner configurarScanner(boolean modoTeste) {
        if (modoTeste) {
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
        if (numero <= 0) throw new IllegalArgumentException("Número inválido!");
        return numero;
    }

    private static String obterAgencia(Scanner scanner) {
        System.out.println("Digite a agência (XXXX-X):");
        String agencia = scanner.nextLine();
        if (!agencia.matches("\\d{4}-\\d")) throw new IllegalArgumentException("Formato inválido!");
        return agencia;
    }

    public static String obterNomeCliente(Scanner scanner) {
        System.out.println("Digite o nome completo:");
        String nome = scanner.nextLine().trim();
        if (nome.split(" ").length < 2) throw new IllegalArgumentException("Nome incompleto!");
        return nome;
    }

    static double obterSaldo(Scanner scanner) {
        System.out.println("Digite o saldo inicial:");
        double saldo = scanner.nextDouble();
        if (saldo < 0) throw new IllegalArgumentException("Saldo negativo!");
        return saldo;

    }


    static void exibirResumoConta(String nome, String agencia, int conta, double saldo) {
        System.out.println("\n═ RESULTADO ═══════════════════════════");
        System.out.printf("│ %-15s %-25s │\n", "Cliente:", nome);
        System.out.printf("│ %-15s %-25s │\n", "Agência:", agencia);
        System.out.printf("│ %-15s %-25d │\n", "Conta:", conta);
        System.out.printf("│ %-15s R$ %-20.2f │\n", "Saldo:", saldo);
        System.out.println("═════════════════════════════════════════");
       
        System.out.printf("Olá %s, obrigado por criar uma conta em nosso banco, sua agência é %s, conta %d e seu saldo %.2f já está disponível para saque.\n", nome, agencia, conta, saldo);


    }
}