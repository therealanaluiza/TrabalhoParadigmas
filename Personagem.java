public class Personagem {
// pode ser abstrata apenas quando tem uma funcao abstrata
    protected int ATK;        // ATAQUE
    protected int DEF;        // DEFESA

    int[] posicao = new int[2];


    // construtor

    public Personagem(int ATK, int DEF, int y, int x) {
        this.setATK(ATK);
        this.setDEF(DEF);
        this.setPosicao(y, x);
    }


    // get set

    public int getATK() {
        return this.ATK;
    }

    public int getDEF() {
        return this.DEF;
    }

    public int[] getposicao() {
        return this.posicao;
    }

    public int setATK(int ATK) {
        this.ATK = ATK;
    }

    public int setDEF(int DEF) {
        this.DEF = DEF;
    }
    
    public int[] setPosicao(int y, int x) {
        this.posicao[0] = y;
        this.posicao[1] = x;
    }

    // subclasses

    public class Player extends Personagem {
        // proxima posicao
        public int[] definePosicao() {
            // construir funcao
        }

        // subclasses

        public class Player1 extends Player {
        }

        public class Player2 extends Player {            
            // recuperar defesa
            public int recuperaDefesa(Player) {
                return; 
            }
        }
    }

    public class Virus extends Personagem {
    }
}




public class Main {

}
