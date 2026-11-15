package personagem.player;

public class Player2 extends Player{
    // --------------------- construtor ---------------------

    public Player2 () {}
    
    // --------------------- metodos ---------------------

    // usar no inicio do jogo
    // nao usa o posicao_atual, sobrecarga

    @Override
    public void inicializaPersonagem(String nome, int[] posicao_necessaria) {        
        // [2,2] eh o inicio
        posicao_necessaria[0] = 2;
        posicao_necessaria[1] = 2;
        vivo = true;

        posicao = posicao_necessaria;

        atk = 1;
        def = 7;
        return;
    }
}
