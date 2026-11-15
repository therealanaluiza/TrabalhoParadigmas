import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import mapa.Mapa;
import personagem.player.*;
import setor.Oculto;
import setor.Setor;
import setor.Privado;
import personagem.*;
import desenho.*;


public class Turno {

    public int realizaTurno(Player p1, Player p2, Mapa m, Scanner comando, DesenhoMapa mapaDesenho, DesenhoSetor setorDesenho){
        int acao_feita = 0;

        if (!p1.getVivo())
            return 1;

        //2 acoes do p1
        while (acao_feita < 2){
            System.out.printf("Acao do Player 1: %d/2\n", acao_feita);
            acao_feita = acao_feita + decideAcao(p1, m, comando, null);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
            mapaDesenho.desenhaMapa(p1, p2, m);
            setorDesenho.desenharSetor(p1, p2, m);
            //checa vitoria
            if (p1.getPosicao()[0] == m.getFonteVirus()[0] && p1.getPosicao()[1] == m.getFonteVirus()[1]){
                System.out.println("Player 1 chegou na fonte do virus!");
                return 2;
            }
        }

        acao_feita = 0;
        
        //2 acoes do p2
        if (p2.getVivo()){

            while (acao_feita < 2){
                System.out.printf("Acao do Player 2: %d/2\n", acao_feita);
                acao_feita = acao_feita + decideAcao(p2, m, comando, p1);
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
                mapaDesenho.desenhaMapa(p1, p2, m);
                setorDesenho.desenharSetor(p1, p2, m);
                //checa vitoria
                if (p2.getPosicao()[0] == m.getFonteVirus()[0] && p2.getPosicao()[1] == m.getFonteVirus()[1]){
                    System.out.println("Player 2 chegou na fonte do virus!");
                    return 2;
                }
            }

        acao_feita = 0;
        }

        ArrayList<Virus> listaInimigos = new ArrayList<Virus>();
        int[] posP1 = p1.getPosicao();
        int[] posP2 = p2.getPosicao();
        
        boolean mesmaPosicao = Arrays.equals(posP1, posP2);

        //turno dos inimigos
        System.out.println("Turno dos Inimigos");
        //se p1 e p2 juntos, sorteia qual player vai ser atacado por cada inimigo
        if (mesmaPosicao){
            Setor s = m.getSetordoMapa(posP1[0], posP1[1]);
            int numInimigos = s.getNumInimigos();

            for(int i = 0; i < numInimigos; i++)
                if(s.getInimigos()[i].getVivo())
                    listaInimigos.add((Virus) s.getInimigos()[i]);

            int playerAtacado;
            for (Virus v : listaInimigos){
                playerAtacado = decidePlayer();
                if (!p2.getVivo()){
                    System.out.println("Player 1 é atacado pelo virus " + v.getATK() + "/" + v.getDEF());
                    realizaAtaque(v, p1, m);
                }
                else{
                    if (playerAtacado == 1){
                        System.out.println("Player 1 é atacado pelo virus " + v.getATK() + "/" + v.getDEF());
                        realizaAtaque(v, p1, m);
                    }
                    else{
                        System.out.println("Player 2 é atacado pelo virus " + v.getATK() + "/" + v.getDEF());
                        realizaAtaque(v, p2, m);
                    }
                }
            }

            //se p1 morreu, perde
            if(!p1.getVivo())
                return 1;
            listaInimigos.clear();
        }

        //se p1 e p2 separados, p1 e p2 são atacados pelos inimigos do setor que estão
        else{
            Setor sPlayer1 = m.getSetordoMapa(posP1[0], posP1[1]);
            Setor sPlayer2 = m.getSetordoMapa(posP2[0], posP2[1]);
            int numInimigosPlayer1 = sPlayer1.getNumInimigos();
            int numInimigosPlayer2 = sPlayer2.getNumInimigos();

            for(int i = 0; i < numInimigosPlayer1; i++)
                if(sPlayer1.getInimigos()[i].getVivo())
                    listaInimigos.add((Virus) sPlayer1.getInimigos()[i]);

            for (Virus v : listaInimigos){
                System.out.println("Player 1 é atacado pelo virus " + v.getATK() + "/" + v.getDEF());
                realizaAtaque(v, p1, m);
            }

            listaInimigos.clear();

            if(p2.getVivo()){

                for(int i = 0; i < numInimigosPlayer2; i++)
                    if(sPlayer2.getInimigos()[i].getVivo())
                        listaInimigos.add((Virus) sPlayer2.getInimigos()[i]);
        
                for (Virus v : listaInimigos){
                    System.out.println("Player 2 é atacado pelo virus " + v.getATK() + "/" + v.getDEF());
                    realizaAtaque(v, p2, m);
                }

                listaInimigos.clear();
            }

            //se p1 morreu, perde
            if(!p1.getVivo())
                return 1;
        }

        return 0;
    }
    
    //funcao que o player escolhe qual acao fazer
    //se nao fazer nada efetivamente, repete a acao (nao da pra perder acoes atoa)
    public int decideAcao(Player p, Mapa m, Scanner comando, Player pVerMesmaPos){
        int decidiu;

        //imprime ações possiveis: 1.movimento, 2.ataque, 3.procura
        System.out.println("Acoes possiveis: ");
        System.out.println("1.Movimento");
        System.out.println("2.Ataque");
        System.out.println("3.Procura");
        if (p instanceof Player2){
            System.out.println("4.Recupera Defesa");
        }
        char c = comando.next().charAt(0);
        if(c == '1'){
            decidiu = preparaMovimento(p, m, comando);
        }
        else if(c == '2'){
            decidiu = preparaAtaque(p, m, comando);
        }
        else if(c == '3'){
            decidiu = preparaProcura(p, m);
        }
        else if (c == '4' && (p instanceof Player2)){
            decidiu = recuperaDefesa(p, pVerMesmaPos,m, comando);
        }
        else{
            System.out.println("Comando invalido!");
            decidiu = 0;
        }
        return decidiu;
    }

    //prepara o mobimento!
    //pega a entrada do jogador e faz algo de acordo:
    //movimento válido : tenta realizar o movimento, retorna 0 se bateu em parede, 1 se se moveu
    //cancelar: cancela e retorna 0
    //movimento invalido: mostra que é invalido retorna 0
    public int preparaMovimento(Player p, Mapa m, Scanner comando){
        System.out.println("Opcoes movimento: ");
        System.out.println("w.cima");
        System.out.println("d.direita");
        System.out.println("s.baixo");
        System.out.println("a.esquerda");
        System.out.println("c.cancelar");
        char direcao = comando.next().charAt(0);
        int conseguiu_mover = 0;


        if (direcao == 'w' || direcao == 'd' ||direcao == 's' || direcao == 'a')
            conseguiu_mover = realizaMovimento(p, direcao, m);
        else if (direcao == 'c'){
            System.out.println("Cancelando...");
            return 0;
        }
        else{
            System.out.println("Movimento invalido!");
            return 0;
        }
        return conseguiu_mover;
    }
    
    //prepara o ataque!
    //retorna 1 se não selecionou morto 0 se selecionou morto
    public int preparaAtaque(Player p, Mapa m, Scanner comando){
        //pega setor do player
        int[] pos = p.getPosicao().clone();
        int posY = pos[0];
        int posX = pos[1];
        Setor s = m.getSetordoMapa(posY, posX);

        //pega vetor dos inimigos no setor e quantidade de inimigos
        Personagem[] inimigos = s.getInimigos();
        int quantidade_inimigos = s.getNumInimigos();
        int i;
        int conseguiu_atacar;

        if (inimigos == null || quantidade_inimigos == 0) {
            System.out.println("Não há inimigos neste setor para atacar!");
            return 0;
        }

        //imprime os vivos como opção de ataque
        for(i = 0; i < quantidade_inimigos; i++){
            if(inimigos[i] != null && inimigos[i].getVivo())
                System.out.println((i + 1) + ".atacar inimigo " + (i + 1));
        }

        //imprime: 0.cancelar
        System.out.println("0.Cancelar");
        int inimigo_selecionado = comando.nextInt();
        if (inimigo_selecionado == 0){
            System.out.println("Cancelando...");
            return 0;
        }

        //casos invalidos (inimigo negativo ou maior que vetor)
        if (inimigo_selecionado < 1 || inimigo_selecionado > quantidade_inimigos){
            System.out.println("Inimigo invalido!");
            return 0;
        }
        
        //nao vai conseguir se inimigo esta morto (supondo inimigo em vetores e nao vamos remover inimigos mortos)
        conseguiu_atacar = realizaAtaque(p, inimigos[inimigo_selecionado - 1], m);

        return conseguiu_atacar;
    }

    //só pq os movimento e ataque tmb tem prepara
    //retorna 1 se procurou, 0 se já foi ou esta em setor privado
    public int preparaProcura(Player p, Mapa m){
        System.out.println("Procurando...");
        return realizaProcura(p, m);
    }

    public int recuperaDefesa(Player p2, Player p1, Mapa m, Scanner comando){
        if (p1 != null && Arrays.equals(p1.getPosicao(), p2.getPosicao())){
            System.out.println("Escolha jogador para curar: 1.Player 1, 2.Player 2");
            System.out.println("0.Cancelar");
            int escolha = comando.nextInt();
            if (escolha == 1){
                p1.setDEF(p1.getDEF() + 2);
                System.out.println("Player 1 recupera 2 de defesa!");
                return 1;
            }
            else if (escolha == 2){
                p2.setDEF(p2.getDEF() + 2);
                System.out.println("Player 2 recupera 2 de defesa!");
                return 1;
            }
            else if(escolha == 0){
                System.out.println("Cancelando...");
                return 0;
            }
            else{
                System.out.println("Escolha invalida!");
                return 0;
            }
        }
        else{
            System.out.println("Player 2 se cura? 1.Sim 0.Nao");
            int escolha = comando.nextInt();
            if (escolha == 1){
                p2.setDEF(p2.getDEF() + 2);
                System.out.println("Player 2 recupera 2 de defesa!");
                return 1;
            }
            else if(escolha == 0){
                System.out.println("Cancelando...");
                return 0;
            }
            else{
                System.out.println("Escolha invalida!");
                return 0;
            }
        }
    }

    //movimenta boneco
    //0 = não movimentou
    //1 = movimentou
    public int realizaMovimento(Player p, char d, Mapa m){
        //pega setor do mapa
        int[] pos = p.getPosicao().clone();
        int posY = pos[0];
        int posX = pos[1];
        Setor s = m.getSetordoMapa(posY, posX);

        //ve se mudou de posição, retorna 0 se não mudou
        int mudou_pos = p.definePosicao(d, p.getPosicao(), s);
        if (mudou_pos == 0){
            System.out.println("Parede");
            return 0;
        }

        //pega setor novo
        //para inicializar os inimigos, e marcar que já foi visitado para não reiniciar inimigos
        int[] pos_final = p.getPosicao().clone();
        posY = pos_final[0];
        posX = pos_final[1];
        Setor s_final = m.getSetordoMapa(posY, posX);
        s_final.inicializaSetor();
        return 1;
    }
    

    //retorna 1 se ataque foi bem sucedido (ou errou por chance, virus ou jogador)
    //retorna 0 se selecionou alguém morto ou caso nenhum outro return aconteceu (como isso aconteceria???)
    //mensagem de acerto? mostrar dano dado e vida resultante do defensor?
    public int realizaAtaque(Personagem ofensivo, Personagem defensivo, Mapa m){
        //calcula dano
        int dano = ofensivo.getATK();
        int defesa = defensivo.getDEF();
        int defesa_final = defesa - dano;

        //pega setor
        int[] pos = ofensivo.getPosicao().clone();
        int posY = pos[0];
        int posX = pos[1];
        Setor s = m.getSetordoMapa(posY, posX);

        if (!ofensivo.getVivo() || !defensivo.getVivo()){
            if (ofensivo instanceof Player)
                System.out.println("Inimigo morto");
            return 0;
        }

        //faz o ataque
        //se for virus 50% de chance de acertar
        //se for player em setor oculto, 50% de chance de acertar
        //se for player em outros setores, acerta
        if (ofensivo instanceof Virus){
            boolean acertou = erraOuAcerta();
            if(acertou){
                System.out.println("Inimigo acerta o ataque");
                defensivo.setDEF(defesa_final);
            }
            else{
                System.out.println("Inimigo erra o ataque");
            }
            return 1;
        }

        else if (ofensivo instanceof Player){
            if(s instanceof Oculto){
                boolean acertou = erraOuAcerta();
                if (acertou){
                    defensivo.setDEF(defesa_final);
                    System.out.println("Player realiza ataque");
                }
                else
                    System.out.println("Player erra o ataque");
            }
            else{
                defensivo.setDEF(defesa_final);
                System.out.println("Player realiza ataque");
            }
            return 1;
        }
        return 0;
    }
    
    //retorna 0 se já foi feito procura ou se for setor privado
    //1 caso contrário (procura feita)
    public int realizaProcura(Player p, Mapa m){
        //pega setor
        int[] pos = p.getPosicao().clone();
        int posY = pos[0];
        int posX = pos[1];
        Setor s = m.getSetordoMapa(posY, posX);

        //faz procura retorna -1 caso privado ou já procurado
        int valorDaProcura = s.fazProcura();
        if (s instanceof Privado){
            System.out.println("Setor privado, procura indisponivel");
            return 0;
        }
        if (valorDaProcura == -1){
            System.out.println("Procura ja realizada neste setor");
            return 0;
        }

        if (valorDaProcura < 4){
            System.out.println("Nada encontrado");
        }
        else if (valorDaProcura == 4){
            if (p instanceof Player1)
                System.out.println("Player 1 ganha 1 de defesa!");
            else if (p instanceof Player2)
                System.out.println("Player 2 ganha 1 de defesa!");
            p.setDEF(p.getDEF() + 1);
        }
        else if (valorDaProcura == 5){
            if (p instanceof Player1)
                System.out.println("Player 1 ganha 2 de defesa!");
            else if (p instanceof Player2)
                System.out.println("Player 2 ganha 2 de defesa!");
            p.setDEF(p.getDEF() + 2);
        }
        else if (valorDaProcura == 6){
            System.out.println("Inimigos no setor perdem 1 de vida");
            Personagem[] inimigos = s.getInimigos();
            int num_inimigos = s.getNumInimigos();
            int i;

            if(inimigos != null){
                for (i = 0; i < num_inimigos; i++){
                    inimigos[i].setDEF(inimigos[i].getDEF() - 1);
                }
            }
        }
        
        return 1;
    }

    //0 erra 1 acerta
    public boolean erraOuAcerta() {
        int zeroOuUm = ThreadLocalRandom.current().nextInt(0, 2);
        if (zeroOuUm == 0){
            return false;
        }
        else
            return true;
    }

    //decide qual player vai ser atacado pelo inimigo, caso estejam juntos
    //1 = p1, 0 = p2
    public int decidePlayer(){
        int zeroOuUm = ThreadLocalRandom.current().nextInt(0, 2);
        return zeroOuUm;
    }
}
