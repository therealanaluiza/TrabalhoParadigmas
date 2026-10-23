package personagem;

// INCOMPLETO

// falta testar
// inicializaPersonagem incompleto - e se nao for nenhum dos dois

public class Player implements Personagem{

    // --------------------- metodos ---------------------

    // inicializa os players
    // usar no inicio do jogo
    // nao usa o posicao_atual, sobrecarga
    public Personagem inicializaPersonagem(String nome) {
        // [3,3] eh o inicio
        posicao[0] = 3;
        posicao[1] = 3;
        vivo = TRUE;

        // numeros de acordo com o enunciado
        if (nome == "Player1") {
            ATK = 2;
            DEF = 6;
            return Personagem;
        } else if (nome == "Player2") {
            ATK = 1;
            DEF = 7;
            return Personagem;
        }

        // nao sei o que faz se for nenhum dos dois
        // tem que fazer alguma coisa?

    }

    // define proxima posicao de acordo com o comando digitado no teclado
    // recebe entrada do usuario e a posicao atual do player
    public int[] definePosicao (char comando, int[] posicao_atual) {
        int y = posicao_atual[0];
        int x = posicao_atual[1];

        // cria variavel para armazenar nova posicao
        int[] prox_posicao = new int[2];

        // logica de movimentacao
        if (comando == 'w') {
            x--;
        } else if (comando == 's') {
            x++;
        } else if (comando == 'a') {
            y--;
        } else if (comando == 'd') {
            y++;
        }

        // teste de validade da operacao
        // passou as paredes do tabuleiro
        // retorna a posicao atual caso nao seja valido
        if (x > 4 || y > 4) {
            System.out.println("muito longe");
            return posicao_atual;
        } else if (x < 1 || y < 1) {
            System.out.println("muito perto");
            return posicao_atual;
        }
        
        // novas array de posicao
        prox_posicao[0] = y;
        prox_posicao[1] = x;

        // retorna a nova posicao
        return prox_posicao;
    }
}
