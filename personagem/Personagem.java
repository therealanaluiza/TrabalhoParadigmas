package personagem;

public interface Personagem {
    public int getATK();
    public int getDEF();
    public int[] getPosicao();
    public boolean getVivo();

    // recebe nome do personagem e posicao que deve estar
    public void inicializaPersonagem(String nome, int[] posicao_necessaria);
}