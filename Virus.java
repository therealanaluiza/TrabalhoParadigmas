package personagem;
import java.util.Random;  // biblioteca de random indicada pelo enunciado

public class Virus implements Personagem {

    // --------------------- metodos ---------------------

    // inicializa os inimigos
    // usar em loop assim que Setor.visitado = TRUE de um setor
    public Personagem inicializaPersonagem(String nome, int[] posicao_setor) {
        // vai para a posicao necessaria (do setor)
        posicao = posicao_setor;
        vivo = TRUE;
        
        // uso de random de acordo com a biblioteca e enunciado
        Random aleatorio = new Random();
        int num;
        num = aleatorio.nextInt(3);

        // exemplo: [2,2] porque foi o pedido pelo enunciado
        ATK = num;
        DEF = num;

        // retorna o inimigo
        return Personagem;
    }
}