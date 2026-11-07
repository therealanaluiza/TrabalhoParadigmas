package personagem.player;

public class Player2 extends Player{
    // --------------------- construtor ---------------------

    public Player2 () {}
    
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

        atk = 1;
        def = 7;
        return;
    }
}
