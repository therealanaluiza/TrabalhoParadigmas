package setor;
import java.util.Random;

public class Normal extends Setor {

    public Normal (int[] posicao, int[] lados, int numInimigos){
        super (posicao, lados, numInimigos);
    }

    private Random aleatorio = new Random();

    @Override
    // faz o sorteio de 1 à 6 normalmente.
    public int fazProcura (){
        if (procurado)
            return -1;
        procurado =  true;
        
        int num;
        num = aleatorio.nextInt(6) + 1;
        return num;
    }
    
}