package personagem;
import java.util.Random;  // biblioteca de random indicada pelo enunciado

// aqui o virus vai pra posicao que vai ser apontada por variavel
// dentro da main Jogo
// entao deve-se prestar bastante atencao para onde vai
public class Virus implements Personagem {
    // quais atributos as subclasses vao herdar
    protected int atk;
    protected int def;
    protected int[] posicao;
    protected boolean vivo;

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

    // --------------------- metodos ---------------------

    // inicializa os inimigos
    // usar em loop assim que Setor.visitado = TRUE de um setor
    @Override
    public void inicializaPersonagem(String nome, int[] posicao_necessaria) {                
        // vai para a posicao necessaria (do setor)
        posicao = posicao_necessaria;
        vivo = true;
        
        // uso de random de acordo com a biblioteca e enunciado
        Random aleatorio = new Random();
        int num;
        num = aleatorio.nextInt(3);

        // exemplo: [2,2] porque foi o pedido pelo enunciado
        atk = num;
        def = num;
    }
}