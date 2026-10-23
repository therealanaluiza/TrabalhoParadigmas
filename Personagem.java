package personagem;

public interface Personagem {
    public int ATK;
    public int DEF;
    public int[] posicao = new int[2];
    public boolean vivo;

    // retorna o personagem feito
    // recebe nome do personagem e posicao que deve estar
    public Personagem inicializaPersonagem(String nome, int[] posicao_necessaria);
}