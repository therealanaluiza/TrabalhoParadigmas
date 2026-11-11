package desenho;

import personagem.player.Player;

public class DesenhoTexto implements Desenho {

    // para desenhar o titulo junto
    private DesenhoTitulo titulo;

    public DesenhoTexto() {
        titulo = new DesenhoTitulo();
    }

    @Override
    public void desenhar (int situacao) {
        if (situacao == 2) {
            System.out.println("          Bem-vindo!          ");
            System.out.println("       Os comandos são:       ");
            System.out.println("       |a - esquerda  |       ");
            System.out.println("       |w - para cima |       ");
            System.out.println("       |s - para baixo|       ");
            System.out.println("       |d - direita   |       ");
            System.out.println("       |r - ataque    |       ");
            System.out.println("   Pressione 'c' para começar   ");
        } else if (situacao == 3) {
            System.out.println();
            System.out.println("          O jogo acabou!       ");
            System.out.println("       Me dá 100 por favor!       ");
            System.out.println();
        } else if (situacao == 4) {
            System.out.println("       Comando inválido       ");
        }

    }

    @Override
    public void desenhaMapa(Player player1, Player player2) {}
}
