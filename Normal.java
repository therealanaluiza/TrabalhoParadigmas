import java.Util.Random;

public class Normal extends Setor {

    public Normal (int[] posicao, int[] lados, int numInimigos){
        super (posicao, lados, numInimigos);
    }

    private Random aleatorio = new Random();

    // faz o sorteio de 1 à 6 normalmente.
    @Override
    public void fazProcura (){
        if (procurado)
            return -1;
        procurado =  true;
        
        Random aleatorio = new Random ();
        int num;
        num = aleatorio.nextInt(6) + 1;

    }
    
}