package desenho;
import personagem.player.Player;
import setor.Setor;

public class DesenhoMapa implements Desenho {
    protected char vertical = '|';
    protected char horizontal = '-';


    // para desenhar o titulo junto
    private DesenhoTitulo titulo;

    public DesenhoMapa() {
        titulo = new DesenhoTitulo();
    }

    private boolean portaVisivel(Setor s, int lado) {
        return s.isVisitado() && s.temPorta(lado);
    }
    
    @Override
    public void desenhaMapa(Player player1, Player player2, Setor[][] setores) {        
        int[] posicao1;
        int[] posicao2;
        
        int linha = 0;
        //int coluna = 1;

        // faz linha - vazio
        for (int j = 1; j <= 5; j++) {
            for (int i = 1; i <= 5; i++) {
                System.out.print(vertical);

                boolean porta = portaVisivel(setores[j - 1][i - 1], 0);
                if (j > 1 && portaVisivel(setores[j - 2][i - 1], 2)) {
                    porta = true;
                }

                if (porta) {
                    System.out.print(" * ");
                } else {
                    for (int k = 1; k <= 3; k++) {
                        System.out.print(horizontal);
                    }
                }
            }

            System.out.print(vertical);

            // pula linha
            System.out.println();
            linha ++;

            // aqueles vazios para fazer os quadrados maiores
            // e colocar os players
            for (int l = 1; l <= 5; l++) {
                // pega as posicoes atualizadas dos players
                posicao1 = player1.getPosicao();
                posicao2 = player2.getPosicao();

                boolean porta = portaVisivel(setores[linha - 1][l - 1], 3);
                if (l > 1 && portaVisivel(setores[linha - 1][l - 2], 1)) {
                    porta = true;
                }

                if (porta) {
                    System.out.print('*');
                } else {
                    System.out.print(vertical);
                }

                // se p1 e p2 estarem no mesmo lugar, aparece apenas p1 no mapa
                // isso eh baseado no enunciado
                if (l == 3 && linha == 3) {
                    System.out.print(" C ");
                } else if (posicao1[0] == l && posicao1[1] == linha) {
                    System.out.print("P1 ");
                } else if (posicao2[0] == l && posicao2[1] == linha) {
                    System.out.print("P2 ");
                } else {
                    System.out.print("   ");
                } 
            }
            
            // faz a ultima barra da linha
            System.out.println(vertical);
        }
        
        // faz ultima linha
        for (int i = 1; i <= 5; i++) {
            System.out.print('|');
            for (int k = 1; k <= 3; k++) {
                System.out.print(horizontal);
            }
        }

        // faz a ultima barra
        System.out.println(vertical);

        System.out.println();
    }

    @Override 
    public void desenhar (int situacao) {}
}
