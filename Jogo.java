import desenho.*;
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
         
        Desenho titulo, texto, mapa;

        titulo = new DesenhoTitulo(); 
        texto = new DesenhoTexto();
        mapa = new DesenhoMapa();

        // inicializa players no jogo
        Player player1, player2;
        player1 = new Player1();
        player2 = new Player2();

        player1.inicializaPersonagem("Player 1", new int[2]);
        player2.inicializaPersonagem("Player 2", new int[2]);
    
        // imprime o inicio
        titulo.desenhar(0);
        texto.desenhar(2);

        // parametros que indicam se o jogo comeca/continua ou nao
        boolean jogo_continua = false;
        int turno = 1;
        
        // vamos ver se comeca mesmo
        Scanner entrada = new Scanner(System.in);
        char comando = entrada.next().charAt(0);

        // começar o jogo
        if (comando == 'c') {
            jogo_continua = true;
            turno = 1;
        }

        // parametrizando para teste
        int[] pos = new int[2];
        pos[0] = 1;
        pos[1] = 2;
        Virus virus1;
        virus1 = new Virus();
        virus1.inicializaPersonagem("Vírus 1", pos);

        int numTurnos = 1;

        while (jogo_continua == true && numTurnos < 26) {
            System.out.print("Digite o movimento: ");
            comando = entrada.next().charAt(0);

            if (comando == '0') {
                jogo_continua = false;
                break;
            }

            if (turno == 1) {
                player1.definePosicao(comando, player1.getPosicao());
            } else if (turno == 2) {
                player2.definePosicao(comando, player2.getPosicao());
            }

            //System.out.println(Arrays.toString(player1.getPosicao()));
            //System.out.println(Arrays.toString(player2.getPosicao()));

            titulo.desenhar(0);
            mapa.desenhaMapa(player1, player2);

            if (turno == 1) {
                turno ++;
            } else {
                turno = 1;
            }
            numTurnos ++;
      }
      texto.desenhar(3);
    }
}
