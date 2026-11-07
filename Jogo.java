import java.util.*;
import personagem.Virus;
import personagem.player.*;


// aqui está atualizando o player 1 e 2 quando se mexe
// a mudança disso tem que vir de turno que vai mudando o pessoal que aparece
// a mudança que vai de acordo com as portas deve vir de setor+turno
public class Jogo {

    // char = comandos
    public void main () {
        // vai receber uma letra do teclado do usuario
        // vai fazer uma acao

        // inicio do jogo
        // printar BEM VINDO E COMANDOS BÁSICOS

        // se clicar enter, começa o jogo

        // começar o jogo

        Player player1, player2;
        player1 = new Player1();
        player2 = new Player2();

        // inicializa players no jogo

        player1.inicializaPersonagem("Player 1", new int[2]);
        player2.inicializaPersonagem("Player 2", new int[2]);

        boolean jogo_continua = true;
        // desenhar mapa

        while (jogo_continua == true) {

        // parametrizando para teste
        int[] pos = new int[2];
        pos[0] = 1;
        pos[1] = 2;

        Virus virus1;
        virus1 = new Virus();

        virus1.inicializaPersonagem("Vírus 1", pos);

        Scanner entrada = new Scanner(System.in);
    
        while (jogo_continua = true) {
            System.out.print("Digite o movimento: ");
            char comando = entrada.next().charAt(0);

            if (comando == '0') {
                jogo_continua = false;
                break;
            }

            player1.definePosicao(comando, player1.getPosicao());
            player2.definePosicao(comando, player2.getPosicao());

            System.out.println(Arrays.toString(player1.getPosicao()));
        }
      }
      String loucura = ("deu boa");
      System.out.println(loucura);
      return;
    }
}
