import desenho.*;
import mapa.Mapa;
import setor.*;

import java.util.*;
import personagem.*;
import personagem.player.*;

//Grupo:
//Analuiza Alves da Cruz ; GRR20254771
//Angelo Nkialulendo Malungu ; GRR20209982
//Pedro Henrique Dalazoana dos Passos; GRR20251993

public class Jogo {

    //constantes usadas
    public static final String[] setoresNoMapa = {"Normal", "Oculto", "Privado", "Normal", "Normal",
                                                "Oculto", "Oculto", "Normal", "Privado", "Oculto",
                                                "Privado", "Normal", "Normal", "Oculto", "Normal",
                                                "Normal", "Oculto", "Normal", "Privado", "Privado",
                                                "Normal", "Normal", "Oculto", "Normal", "Privado"};

    public static final int[][][] portasNoMapa = {
        { {0,1,1,0}, {0,1,0,1}, {0,1,0,1}, {0,0,1,1}, {0,0,0,0} },   
        { {1,0,1,0}, {0,1,0,0}, {0,0,1,1}, {1,1,0,0}, {0,0,1,1} },   
        { {1,1,0,0}, {0,1,1,1}, {1,1,1,1}, {0,0,0,1}, {1,0,1,0} },   
        { {0,0,0,0}, {1,0,0,0}, {1,0,1,0}, {0,1,1,0}, {1,0,1,1} },   
        { {0,0,0,0}, {0,0,0,0}, {1,1,0,0}, {1,1,0,1}, {1,0,0,1} }   
        };

    public static final int[] inicio = {2, 2};

    public static final int[] fim = {0, 3};

    public static void main (String[] args) {

        //declara variaveis usadas
        Scanner comando = new Scanner(System.in);

        boolean Jogando = false;
        int turno = 1;
    
        Desenho tituloDesenho, textoDesenho;

        DesenhoSetor setorDesenho = new DesenhoSetor();
        DesenhoMapa mapaDesenho = new DesenhoMapa();

        tituloDesenho = new DesenhoTitulo(); 
        textoDesenho = new DesenhoTexto();
        mapaDesenho = new DesenhoMapa();
        setorDesenho = new DesenhoSetor();

        // inicializa players no jogo
        Player player1, player2;
        player1 = new Player1();
        player2 = new Player2();

        player1.inicializaPersonagem("Player 1", new int[2]);
        player2.inicializaPersonagem("Player 2", new int[2]);


        //declara e inicializa mapa
        Mapa mapa;
        mapa = new Mapa(5, 5);
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                mapa.setSetordoMapa(setoresNoMapa[i*5+j], new int[]{i, j}, portasNoMapa[i][j]);
            }
        }

        mapa.setInicio(inicio);
        mapa.setFonteVirus(fim);


        //mensagem inicial
        tituloDesenho.desenhar(0);
        textoDesenho.desenhar(2);

        char entrada = comando.next().charAt(0);
            if (entrada == 'c') {
                Jogando = true;
            }
            else{
                System.out.println("Saindo do jogo...");
                return;
            }

        //inicia turno
        Turno t = new Turno();
        int situacao = 0;
        
        //loop do jogo
        while(Jogando && turno <= 25){
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Turno: " + turno);
            mapaDesenho.desenhaMapa(player1, player2, mapa);
            setorDesenho.desenharSetor(player1, player2, mapa);

            //chama funcao principal que realiza as acoes dos jogadores e inimigos.
            //0 continua, 1 game over (p1 morto), 2 vitoria (chegou na fonte do virus)
            situacao = t.realizaTurno(player1, player2, mapa, comando, mapaDesenho, setorDesenho);

            if (situacao == 1){
                Jogando = false;
            }
            else if (situacao == 2){
                Jogando = false;
            }
            turno ++;
        }

        if (turno > 25){
            System.out.println("O jogo acabou. Ninguém chegou na fonte do vírus...");
        }
        else if(situacao == 1){
            System.out.println("Game over. Player 1 Morto...");
        }
        else if(situacao == 2){
            System.out.println("Vitoria! Chegaram na fonte do virus!");
        }
        textoDesenho.desenhar(3);

        comando.close();
        return;
    }
}
