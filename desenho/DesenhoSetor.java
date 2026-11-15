package desenho;
/*
import java.util.Arrays;

import mapa.Mapa;
import personagem.Virus;
import personagem.player.Player;
import setor.Normal;
import setor.Oculto;
import setor.Privado;
import setor.Setor;

public class DesenhoSetor implements Desenho {
    @Override
    public void desenhar(int num) {}

    @Override 
    public void desenhaMapa(Player player1, Player player2, Mapa m){}

   public void desenharSetor(Player p1, Player p2, Mapa m) {
    int[] posP1 = p1.getPosicao().clone();
    int[] posP2 = p2.getPosicao().clone();

    int[] posP1Visual = { posP1[0] + 1, posP1[1] + 1 };
    int[] posP2Visual = { posP2[0] + 1, posP2[1] + 1 };

    if (Arrays.equals(posP1, posP2)) {
        // --- MESMO SETOR ---
        Setor s = m.getSetordoMapa(posP1[0], posP1[1]);
        String tipoStr = obterTipoSetor(s);
        
        System.out.println("Setor " + Arrays.toString(posP1Visual));
        System.out.println(tipoStr);
        desenharCaixaUnica(s, p1, p2);
        
    } 
    else {
        // --- SEPARADOS: Desenha as duas caixas lado a lado ---
        Setor s1 = m.getSetordoMapa(posP1[0], posP1[1]);
        Setor s2 = m.getSetordoMapa(posP2[0], posP2[1]);
        
        // Cabeçalhos dos dois setores lado a lado
        System.out.printf("Setor %-18s   Setor %s\n", Arrays.toString(posP1Visual), Arrays.toString(posP2Visual));
        System.out.printf("%-20s   %s\n", obterTipoSetor(s1), obterTipoSetor(s2));
        
        // Renderiza as caixas combinadas horizontalmente linha por linha
        desenharDuasCaixasLadoALado(s1, p1, s2, p2);
    }
    }

    // Descobre o tipo do setor usando instanceof
    private String obterTipoSetor(Setor s) {
        if (s instanceof Normal) {
            return "Normal";
        } else if (s instanceof Privado) {
            return "Privado";
        } else if (s instanceof Oculto) {
            return "Oculto";
        }
        return "Desconhecido";
    }

    // Desenha apenas 1 caixa (para quando estão no mesmo setor)
    private void desenharCaixaUnica(Setor s, Player p1, Player p2) {
        int largura = 22; // Tamanho ideal para caber os elementos centralizados
        String borda = "|" + "-".repeat(largura - 2) + "|";

        System.out.println(borda);

        // Linha 1: Inimigos (formato Atq/Def separados por espaço)
        System.out.println(formatarInimigos(s, largura));
        
        // Linha vazia de espaçamento interna
        System.out.println("|" + " ".repeat(largura - 2) + "|");

        // Linha 2: Nomes/Indicadores dos Jogadores (P1 e P2)
        String linhaPlayers = "|  " + (p1 != null ? "P1" : "  ") + "        " + (p2 != null ? "P2" : "  ") + "  |";
        System.out.println(linhaPlayers);

        // Linha 3: Atributos dos Jogadores (Ataque/Defesa)
        String atqP1 = p1 != null ? p1.getATK() + "/" + p1.getDEF() : "   ";
        String atqP2 = p2 != null ? p2.getATK() + "/" + p2.getDEF() : "   ";
        String linhaAtrib = String.format("|  %-5s     %-5s  |", atqP1, atqP2);
        System.out.println(linhaAtrib);

        System.out.println(borda);
    }

    private String formatarInimigos(Setor s, int larguraCaixa) {
    // Pegamos como Object[] ou Personagem[] dependendo do que o seu método s.getInimigos() retorna
    // Usar Object[] é mais seguro para evitar qualquer ClassCastException genérica
    Object[] inimigos = s.getInimigos(); 
    int numInimigos = s.getNumInimigos();
    int total = Math.min(numInimigos, 3);

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < total; i++) {
        if (inimigos != null && inimigos[i] != null) {
            // Verificamos se o objeto realmente é um Virus antes de chamar os métodos
            if (inimigos[i] instanceof Virus) {
                Virus v = (Virus) inimigos[i];
                sb.append(v.getATK()).append("/").append(v.getDEF()).append(" ");
            }
        }
    }
    
    String conteudo = sb.toString().trim();
    if (conteudo.isEmpty()) {
        conteudo = "Vazio";
    }

    // Centraliza os inimigos dentro da caixa
    int espacosDisponiveis = larguraCaixa - 2 - conteudo.length();
    int esquerda = espacosDisponiveis / 2;
    int direita = espacosDisponiveis - esquerda;

    return "|" + " ".repeat(Math.max(0, esquerda)) + conteudo + " ".repeat(Math.max(0, direita)) + "|";
}
    // Desenha duas caixas separadas lado a lado no terminal
    private void desenharDuasCaixasLadoALado(Setor s1, Player p1, Setor s2, Player p2) {
        int largura = 22;
        String borda = "+" + "-".repeat(largura - 2) + "+";
        String linhaVazia = "|" + " ".repeat(largura - 2) + "|";

        // Topo das duas caixas
        System.out.println(borda + "   " + borda);

        // Linha de Inimigos
        System.out.println(formatarInimigos(s1, largura) + "   " + formatarInimigos(s2, largura));
        
        // Espaçamento interno
        System.out.println(linhaVazia + "   " + linhaVazia);

        // Linha de Identificação dos Jogadores
        // Caixa 1 foca no P1, Caixa 2 foca no P2 (se existirem na chamada)
        String l1Players = "|  " + (p1 != null ? "P1" : "  ") + "               |";
        String l2Players = "|  " + (p2 != null ? "P2" : "  ") + "               |";
        System.out.println(l1Players + "   " + l2Players);

        // Linha de Atributos dos Jogadores
        String atqP1 = p1 != null ? p1.getATK() + "/" + p1.getDEF() : "   ";
        String atqP2 = p2 != null ? p2.getATK() + "/" + p2.getDEF() : "   ";
        
        String l1Atrib = String.format("|  %-15s|", atqP1);
        String l2Atrib = String.format("|  %-15s|", atqP2);
        System.out.println(l1Atrib + "   " + l2Atrib);

        // Base das duas caixas
        System.out.println(borda + "   " + borda);
        
    }
}*/

import mapa.*;
import java.util.Arrays;
import personagem.Virus;
import personagem.player.Player;
import setor.Setor;
import setor.Normal;
import setor.Privado;
import setor.Oculto;

public class DesenhoSetor implements Desenho {
    @Override
    public void desenhar(int num) {}

    @Override 
    public void desenhaMapa(Player player1, Player player2, Mapa m){}

   public void desenharSetor(Player p1, Player p2, Mapa m) {
    int[] posP1 = p1.getPosicao().clone();
    int[] posP2 = p2.getPosicao().clone();

    //Para mostrar para o jogador de 1 até 5, nao de 0 ate 4
    int[] posP1Visual = { posP1[0] + 1, posP1[1] + 1 };
    int[] posP2Visual = { posP2[0] + 1, posP2[1] + 1 };

    //desenha 1 caixa, estao na mesma posicao
    if (Arrays.equals(posP1, posP2)) {
        Setor s = m.getSetordoMapa(posP1[0], posP1[1]);
        String tipoStr = obterTipoSetor(s);
        
        System.out.println("Setor " + Arrays.toString(posP1Visual));
        System.out.println(tipoStr);
        desenharCaixaUnica(s, p1, p2);
        
    } 
    //desenha 2 caixas, estao em posicoes diferentes
    else {
        Setor s1 = m.getSetordoMapa(posP1[0], posP1[1]);
        Setor s2 = m.getSetordoMapa(posP2[0], posP2[1]);
        
        String tituloS1 = "Setor " + Arrays.toString(posP1Visual);
        String tituloS2 = "Setor " + Arrays.toString(posP2Visual);
        
        System.out.printf("%-25s   %s\n", tituloS1, tituloS2);
        System.out.printf("%-25s   %s\n", obterTipoSetor(s1), obterTipoSetor(s2));
        
        desenharDuasCaixasLadoALado(s1, p1, s2, p2);
    }
  }

  //tipo do setor para mostrar no desenho
    private String obterTipoSetor(Setor s) {
        if (s instanceof Normal) {
            return "Normal";
        } else if (s instanceof Privado) {
            return "Privado";
        } else if (s instanceof Oculto) {
            return "Oculto";
        }
        return "Desconhecido";
    }

    //desenha 1 caixa
    private void desenharCaixaUnica(Setor s, Player p1, Player p2) {
        int largura = 22; 
        String borda = "|" + "-".repeat(largura - 2) + "|";

        System.out.println(borda);

        System.out.println(formatarInimigos(s, largura));
        
        System.out.println("|                    |");

        System.out.println("|  P1          P2    |");

        String atqP1 = p1 != null ? p1.getATK() + "/" + p1.getDEF() : "   ";
        String atqP2 = p2 != null ? p2.getATK() + "/" + p2.getDEF() : "   ";
        
        String linhaAtrib = String.format("|  %-7s     %-3s   |", atqP1, atqP2);
        System.out.println(linhaAtrib);

        System.out.println(borda);
    }

    // retorna string usada para imprimir os inimigos do setor, no centro da caixa
    private String formatarInimigos(Setor s, int larguraCaixa) {
        Object[] inimigos = s.getInimigos(); 
        int numInimigos = s.getNumInimigos();
        int total = Math.min(numInimigos, 3);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < total; i++) {
            if (inimigos != null && inimigos[i] != null) {
                if (inimigos[i] instanceof Virus) {
                    Virus v = (Virus) inimigos[i];
                    sb.append(v.getATK()).append("/").append(v.getDEF()).append(" ");
                }
            }
        }
        
        String conteudo = sb.toString().trim();
        if (conteudo.isEmpty()) {
            conteudo = "Vazio";
        }

        int espacosDisponiveis = larguraCaixa - 2 - conteudo.length();
        int esquerda = espacosDisponiveis / 2;
        int direita = espacosDisponiveis - esquerda;

        return "|" + " ".repeat(Math.max(0, esquerda)) + conteudo + " ".repeat(Math.max(0, direita)) + "|";
    }

    //desenha 2 caixas
    private void desenharDuasCaixasLadoALado(Setor s1, Player p1, Setor s2, Player p2) {
    int largura = 22;
    String borda = "|" + "-".repeat(largura - 2) + "|";
    String linhaVazia = "|                    |";

    System.out.println(borda + "   " + borda);

    System.out.println(formatarInimigos(s1, largura) + "   " + formatarInimigos(s2, largura));
    
    System.out.println(linhaVazia + "   " + linhaVazia);

    String l1Players = "|  P1                |";
    String l2Players = "|  P2                |";
    System.out.println(l1Players + "   " + l2Players);

    String atqP1 = p1 != null ? p1.getATK() + "/" + p1.getDEF() : "   ";
    String atqP2 = p2 != null ? p2.getATK() + "/" + p2.getDEF() : "   ";
    
    String l1Atrib = String.format("|  %-18s|", atqP1);
    String l2Atrib = String.format("|  %-18s|", atqP2);
    
    l1Atrib = "|" + "  " + String.format("%-18s", atqP1) + "|";
    l2Atrib = "|" + "  " + String.format("%-18s", atqP2) + "|";
    
    System.out.println(l1Atrib.substring(0, 22) + "   " + l2Atrib.substring(0, 22));

    System.out.println(borda + "   " + borda);
    }
}
