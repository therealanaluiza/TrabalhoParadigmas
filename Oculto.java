import java.util.Random;

public class Oculto extends Setor {
    private int maxDado = 6;
    private Random aleatorio = new Random();

    public Oculto(int[] posicao, int[] lados, int numInimigos) {
        super(posicao, lados, numInimigos);
    }

    // faz o sorteio de 1 a 6. No ataque: par acerta, ímpar erra (50%).
    public int giraDado() {
        return aleatorio.nextInt(maxDado) + 1;
    }

    @Override
    public int fazProcura() {
        if (procurado)
            return -1;
        procurado = true;
        int dado = giraDado();
        return dado;
    }
}