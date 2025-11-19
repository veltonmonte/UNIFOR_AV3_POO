import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Você deseja registrar um novo participante? (s/n)");
        String resposta = scanner.nextLine();

        if(resposta.equals("s")){
            System.out.println("Digite o ID do participante:");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite o nome do participante:");
            String nome = scanner.nextLine();

            System.out.println("Digite o login do participante:");
            String login = scanner.nextLine();

            System.out.println("Digite o email do participante:");
            String email = scanner.nextLine();

            System.out.println("Digite a senha do participante:");
            String senha = scanner.nextLine();

            System.out.println("Digite o telefone do participante:");
            String telefone = scanner.nextLine();

            Participante participante = new Participante(id, nome, login, email, senha, telefone);
            try {
                participante.registrarParticipante();
                System.out.println("Participante registrado com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro ao registrar participante: " + e.getMessage());
            }
        } else {
            System.out.println("Digite seu login:");
            String login = scanner.nextLine();

            System.out.println("Digite sua senha:");
            String senha = scanner.nextLine();
        }
    }
}