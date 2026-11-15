package mapa;

import setor.Setor;

public class Mapa {
    private Setor[][] setores;
    private int[] fonteVirus;
    private int[] inicio;


    public Mapa(int linhas, int colunas) {
        this.setores = new Setor[linhas][colunas];
    }
    
    public void setFonteVirus(int[] fim){
        this.fonteVirus = fim;
    }
    
    public int[] getFonteVirus(){
        return this.fonteVirus;
    }

    public void setInicio(int[] inicio){
        this.inicio = inicio;
        if (setores[inicio[0]][inicio[1]] != null)
             setores[inicio[0]][inicio[1]].setVisitado(true);
    }
    
    public int[] getInicio(){
        return this.inicio;
    }

    public Setor getSetordoMapa(int y, int x){
        return this.setores[y][x];
    }

    public void setSetordoMapa(String tipo, int[] pos, int[] portas) {
        this.setores[pos[0]][pos[1]] = Setor.defineTipoSetor(tipo, pos, portas, 0);
    }
}
