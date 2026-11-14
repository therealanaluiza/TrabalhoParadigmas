import setor.Setor;
import java.util.Random;

public class Mapa {
    private Setor[][] setores = new Setor[5][5];
    private int[] maximos = {5, 5};
    private int[] inicio = {3, 3};
    private int[] fonteVirus = new int[2];

    private static final String[][] TIPOS = {
        {"Normal",  "Oculto",  "Privado", "Normal",  "Normal"},
        {"Oculto",  "Oculto",  "Normal",  "Privado", "Oculto"},
        {"Privado", "Normal",  "Normal",  "Oculto",  "Normal"},
        {"Normal",  "Oculto",  "Normal",  "Privado", "Privado"},
        {"Normal",  "Normal",  "Oculto",  "Normal",  "Privado"}
    };

    // cria os 25 setores e escolhe a fonte de virus
    public Mapa() {
        for (int linha = 1; linha <= 5; linha++) {
            for (int coluna = 1; coluna <= 5; coluna++) {
                int[] pos = {coluna, linha};
                int[] portas = Setor.defineLados(coluna, linha);
                setSetordoMapa(TIPOS[linha - 1][coluna - 1], pos, portas, 0);
            }
        }
        getSetordoMapa(inicio[0], inicio[1]).setVisitado(true);

        defineFonteVirus();
    }

    // devolve o setor da posicao (coluna, linha), de 1 a 5
    public Setor getSetordoMapa(int coluna, int linha) {
        return this.setores[linha - 1][coluna - 1];
    }

    // cria o setor do tipo certo e guarda na posicao dele
    public void setSetordoMapa(String tipo, int[] pos, int[] portas, int numInimigos) {
        Setor s = Setor.defineTipoSetor(tipo, pos, portas, numInimigos);
        this.setores[pos[1] - 1][pos[0] - 1] = s;
    }

    // sorteia um setor que nao seja o centro e que tenha pelo menos uma porta
    public void defineFonteVirus() {
        Random aleatorio = new Random();
        int coluna, linha;
        boolean valido;
        do {
            coluna = aleatorio.nextInt(maximos[0]) + 1;
            linha = aleatorio.nextInt(maximos[1]) + 1;

            boolean eh_centro = (coluna == inicio[0] && linha == inicio[1]);
            boolean tem_porta = false;
            for (int lado : getSetordoMapa(coluna, linha).getLados()) {
                if (lado == 1) {
                    tem_porta = true;
                }
            }
            valido = !eh_centro && tem_porta;
        } while (!valido);

        fonteVirus[0] = coluna;
        fonteVirus[1] = linha;
    }

    public Setor[][] getSetores() {
        return this.setores;
    }

    public int[] getFonteVirus() {
        return fonteVirus;
    }
    public int[] getInicio() {
        return inicio;
    }
    public int[] getMaximos() {
        return maximos;
    }
}
