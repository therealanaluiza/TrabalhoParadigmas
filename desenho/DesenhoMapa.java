package desenho;
import personagem.player.Player;

public class DesenhoMapa implements Desenho {
    protected char vertical = '|';
    protected char horizontal = '-';


    // para desenhar o titulo junto
    private DesenhoTitulo titulo;

    public DesenhoMapa() {
        titulo = new DesenhoTitulo();
    }
    
    @Override
    public void desenhaMapa(Player player1, Player player2) {        
        int[] posicao1;
        int[] posicao2;
        
        int linha = 0;
        //int coluna = 1;

        // faz linha - vazio
        for (int j = 1; j <= 5; j++) {
            for (int i = 1; i <= 5; i++) {
                System.out.print(vertical);
                for (int k = 1; k <= 3; k++) {
                    System.out.print(horizontal);
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

                System.out.print(vertical);

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
