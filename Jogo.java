// NOMES E GRR:
// 1. Angelo Nkialulendo Malungu, GRR20209982
// 2. Analuiza Alves da Cruz, GRR20254771
// 3. Pedro Henrique, GRR


import desenho.*;
import java.util.*;
import personagem.Virus;
import personagem.player.*;
import setor.Setor;  

// aqui está atualizando o player 1 e 2 quando se mexe
// a mudança disso tem que vir de turno que vai mudando o pessoal que aparece
// a mudança que vai de acordo com as portas deve vir de setor+turno
public class Jogo {

    // char = comandos
    public static void main (String[] args) {  
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

        player1.inicializaPersonagem("Player 1", new int[]{3, 3}); 
        player2.inicializaPersonagem("Player 2", new int[]{3, 3});   

        Mapa tabuleiro = new Mapa(); // cria os 25 setores
    
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

            // setor onde o jogador está
            int[] posicao_atual;
            if (turno == 1) {
                posicao_atual = player1.getPosicao();
            } else {
                posicao_atual = player2.getPosicao();
            }
            Setor setor_atual = tabuleiro.getSetordoMapa(posicao_atual[0], posicao_atual[1]);

            //definePosicao recebe o setor e devolve 1 se tem porta para se mover ou 0 (parede)

            int moveu;
            if (turno == 1) {
                moveu = player1.definePosicao(comando, player1.getPosicao(), setor_atual);
            } else {
                moveu = player2.definePosicao(comando, player2.getPosicao(), setor_atual);
            }

            // se moveu, gera os virus do setor novo na primeira visita
            if (moveu == 1) {
                int[] nova_posicao;
                if (turno == 1) {
                    nova_posicao = player1.getPosicao();
                } else {
                    nova_posicao = player2.getPosicao();
                }
                tabuleiro.getSetordoMapa(nova_posicao[0], nova_posicao[1]).inicializaSetor();
            } else {
                System.out.println("Nao ha porta nessa direcao.");
            }

            titulo.desenhar(0);
            mapa.desenhaMapa(player1, player2, tabuleiro.getSetores()); //recebe os setores

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
