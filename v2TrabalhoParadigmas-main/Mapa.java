import setor.Setor;

public class Mapa {
    private Setor[][] setores = new Setor[5][5];

    public Setor getSetordoMapa(int y, int x){
        return this.setores[y][x];
    }
}