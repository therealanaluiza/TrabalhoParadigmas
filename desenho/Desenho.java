package desenho;
import personagem.player.Player;
import setor.Setor;

public interface Desenho {
    void desenhar(int situacao);

    // 0 - nada
    // 1 - titulo
    // 2 - texto.inicio
    // 3 - texto.fim
    // 4 -

    void desenhaMapa(Player player1, Player player2, Setor[][] setores);
}
