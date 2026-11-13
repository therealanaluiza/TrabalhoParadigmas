import setor.Setor;

public class Mapa {
    private Setor[][] setores = new Setor[5][5];

    public Setor getSetordoMapa(int y, int x){
        return this.setores[y][x];
    }

    public Setor setSetordoMapa(String tipo, int[] pos, int[] portas, int numInimigos){
        Setor s = new Setor();
        s.defineTipoSetor(tipo, pos, portas, numInimigos);
    }
}