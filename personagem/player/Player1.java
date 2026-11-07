package personagem.player;

// INCOMPLETO
//java Jogo.java
//Exception in thread "main" java.lang.NullPointerException: Cannot store to int array because "this.posicao" is null
//	at personagem.player.Player1.inicializaPersonagem(Player1.java:21)
//	at Jogo.main(Jogo.java:26)


// falta testar
// inicializaPersonagem incompleto - e se nao for nenhum dos dois

public class Player1 extends Player{
    // --------------------- construtor ---------------------

    public Player1 () {}

    // --------------------- metodos ---------------------

    // usar no inicio do jogo
    // nao usa o posicao_atual, sobrecarga

    @Override
    public void inicializaPersonagem(String nome, int[] posicao_necessaria) {        
        // [3,3] eh o inicio
        posicao_necessaria[0] = 3;
        posicao_necessaria[1] = 3;
        vivo = true;

        posicao = posicao_necessaria;

        atk = 2;
        def = 6;
        return;
    }
}
