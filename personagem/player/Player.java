package personagem.player;

import personagem.Personagem;

public abstract class Player implements Personagem {
    // quais atributos as subclasses vao herdar
    protected int atk;
    protected int def;
    protected int[] posicao;
    protected boolean vivo;

    public Player () {}

    // definicao override de interface
    @Override
    public int getATK() {
        return this.atk;
    }
    @Override
    public int getDEF() {
        return this.def;
    }
    @Override
    public int[] getPosicao() {
        return this.posicao;
    }
    @Override
    public boolean getVivo() {
        return this.vivo;
    }

    // --------------------- metodos ---------------------

    @Override
    public abstract void inicializaPersonagem(String nome, int[] posicao_necessaria);

    // define proxima posicao de acordo com o comando digitado no teclado
    // recebe entrada do usuario e a posicao atual do player
    public int definePosicao (char comando, int[] posicao_atual) {
        int y = posicao_atual[0];
        int x = posicao_atual[1];

        int novo_y = y;
        int novo_x = x;

        
        //System.out.println(Arrays.toString(posicao_atual));

        // logica de movimentacao
        if (comando == 'w') {
            novo_x = novo_x - 1;
        } else if (comando == 's') {
            novo_x = novo_x + 1;
        } else if (comando == 'a') {
            novo_y = novo_y - 1;
        } else if (comando == 'd') {
            novo_y = novo_y + 1;
        }

        // teste de validade da operacao
        // passou as paredes do tabuleiro
        // retorna a posicao atual caso nao seja valido
        if (novo_x > 5 || novo_y > 5) {
            System.out.println("passou de 5");
            return 0;
        } else if (novo_x < 1 || novo_y < 1) {
            System.out.println("muito perto");
            return 0;
        }
        
        posicao[0] = novo_y;
        posicao[1] = novo_x;

        return 1;
    }
}
