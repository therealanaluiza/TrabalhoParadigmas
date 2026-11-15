package personagem.player;

import personagem.Personagem;
import setor.Setor;

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
    public void setDEF(int def) {
        if(def < 1){
            this.def = 0;
            this.vivo = false;
        }
        else{
            this.def = def;
        }
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

    public int definePosicao (char comando, int[] posicao_atual, Setor s) {
        int[] portas = s.getLados();

        if (comando == 'w' && portas[0] == 1) {
            posicao_atual[0] = posicao_atual[0] - 1;
            return 1;
        } else if (comando == 's' && portas[2] == 1) {
            posicao_atual[0] = posicao_atual[0] + 1;
            return 1;
        } else if (comando == 'a' && portas[3] == 1) {
            posicao_atual[1] = posicao_atual[1] - 1;
            return 1;
        } else if (comando == 'd' && portas[1] == 1) {
            posicao_atual[1] = posicao_atual[1] + 1;
            return 1;
        }
    
        return 0;
    }
}
