package setor;

import personagem.Personagem;
import personagem.Virus;
import java.util.Random;

public abstract class Setor {
    protected int[] posicao = new int[2];
    protected Personagem[] inimigos = new Personagem[3];
    protected int[] lados = new int[4];
    protected int numInimigos;
    protected boolean procurado;
    protected boolean visitado;

    public Setor (){}

    public Setor(int[] posicao, int[] lados) {
        this.posicao = posicao;
        this.lados = lados;
        this.procurado = false;
        this.visitado = false;
        
    }
    // Métodos
    public static Setor defineTipoSetor(String tipo, int[] posicao, int[] lados) {
        switch (tipo) {
            case "Oculto":  
                return new Oculto(posicao, lados);
            case "Privado": 
                return new Privado(posicao, lados);
            default:        
                return new Normal(posicao, lados);
        }
    }

    public int fazProcura (){
        return -1;
    }

    // Gera de 1 a 3 vírus aleatórios na primeira visita.
    public void inicializaSetor() {
        if (visitado)
            return;
        visitado = true;

        Random aleatorio = new Random();
        numInimigos = aleatorio.nextInt(3) + 1;

        for (int i = 0; i < numInimigos; i++) {
            Virus v = new Virus();

            // coloca o vírus na posição do setor e sorteia seu ataque e defesa.
            v.inicializaPersonagem("Virus" + (i + 1), posicao); 
            inimigos[i] = v; // guarda o vírus na posição i do vetor de inimigos
        }
    }

    // retorna true se tem porta nos lados
    public boolean temPorta(int lado) {
        if (lados[lado] == 1) {
            return true;
        } else {
            return false;
        }
    }

    //verifica se ainda tem um virus vivo no setor ou não
    public boolean temVirusVivo() {
        for (int i = 0; i < numInimigos; i++) {
            if (inimigos[i].getVivo()) {
                return true;
            }
        }
        return false;
    }


    // Metodos Get/Set
    public int[] getPosicao (){
        return this.posicao;
    }
    public void setPosicao (int[] posicao){
        this.posicao = posicao;
    }

    public Personagem[] getInimigos (){
        return this.inimigos;
    }
    public void setInimigos (Personagem[] inimigos){
        this.inimigos = inimigos;
    }

    public int[] getLados (){
        return this.lados;
    }
    public void setLados (int[] lados){
        this.lados = lados;
    }

    public int getNumInimigos (){
        return this.numInimigos;
    }
    public void setNumInimigos (int numInimigos){
        this.numInimigos = numInimigos;
    }

    public boolean isProcurado() {
        return this.procurado;
    }
    public void setProcurado(boolean procurado) {
        this.procurado = procurado;
    }

    public boolean isVisitado() {
        return this.visitado;
    }
    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
}