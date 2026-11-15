package desenho;

import mapa.Mapa;
import personagem.player.Player;

public class DesenhoTitulo implements Desenho {
    @Override
    public void desenhar (int situacao) {
        System.err.println();
        System.out.println("------------------------------");
        System.out.println("|    Antivírus por um dia    |");
        System.out.println("------------------------------");
    }

    @Override
    public void desenhaMapa(Player player1, Player player2, Mapa m) {}

    @Override
    public void desenharSetor(Player p1, Player p2, Mapa m){}
}
