import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import personagem.player.*;
import setor.Oculto;
import setor.Setor;
import personagem.*;


public class Turno {


    /*lógica:
    ve se p1 ta vivo, se não, acaba
    ação p1
    ação p1
    se p2 ta vivo{
    ação p2
    ação p2
    }
    */
   
    /*
    public int realizaTurno(Player p1, Player p2, Mapa m){
        boolean acao;
        if (!p1.getVivo())
            return 1;
        while(!decideAcao(p1, m)){

        }
        while(!decideAcao(p1, m)){

        }
        while(!decideAcao(p2, m)){

        }
        while(!decideAcao(p2, m)){

        }

        //se p1 junto com p2
        //pega inimigos
        //se separados
        //pega inimigos 1 e inmigos 2
        //turnos inimigos
        return 0;
    }
    */


    public int decideAcao(Player p, Mapa m){
        Scanner comando = new Scanner(System.in);
        int decidiu;

        //imprime ações possiveis: 1.movimento, 2.ataque, 3.procura
        char c = comando.next().charAt(0);
        if(c == '1'){
            decidiu = preparaMovimento(p, m);
        }
        else if(c == '2'){
            decidiu = preparaAtaque(p, m);
        }
        else if(c == '3'){
            decidiu = preparaProcura(p, m);
        }
        else{
            //print comando invalido
            decidiu = 0;
        }
        return decidiu;
    }

    //prepara o mobimento!
    //pega a entrada do jogador e faz algo de acordo:
    //movimento válido : tenta realizar o movimento, retorna 0 se bateu em parede, 1 se se moveu
    //cancelar: cancela e retorna 0
    //movimento invalido: mostra que é invalido retorna 0
    public int preparaMovimento(Player p, Mapa m){
        //imprime opcoes movimento (w.cima, d.direita, s.baixo, a.esquerda, c.cancelar)
        Scanner comando = new Scanner(System.in);
        char direcao = comando.next().charAt(0);
        int conseguiu_mover = 0;


        if (direcao == 'w' || direcao == 'd' ||direcao == 's' || direcao == 'a')
            conseguiu_mover = realizaMovimento(p, direcao, m);
        else if (direcao == 'c'){
            //imprime cancelando
            return 0;
        }
        else{
            //imprime movimento invalido
            return 0;
        }
        return conseguiu_mover;
    }
    
    //prepara o ataque!
    //pega o vetor dos inimigos do setor que o player ta e a quantidade
    //imprime os vivos como opção de ataque (imagino que vamos imprimir os mortos tmb)
    //pega o inimigo selecionado (vai ser numero)
    //0 vai cancelar
    //se selecionar algo menor que -1 (0 já vai ter passado pelo if) ou mais que a quantidade é invalido
    //tenta realizar ataque contra inimigo selecionado (pode ser que seja morto)
    //retorna 1 se não selecionou morto 0 se selecionou morto
    public int preparaAtaque(Player p, Mapa m){
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

        //imprime os vivos
        for(i = 0; i < quantidade_inimigos; i++){
            if(inimigos[i].getVivo())
                //imprime: (i + 1). atacar inimigo i + 1
                i++; //so pra nao ficar dando erro
        }

        //imprime: 0.cancelar
        Scanner comando = new Scanner(System.in);
        int inimigo_selecionado = comando.nextInt();
        if (inimigo_selecionado == 0){
            //cancelando ataque
            return 0;
        }

        if (inimigo_selecionado < 1 || inimigo_selecionado > quantidade_inimigos){
            //imprime inimigo invalido
            return 0;
        }
        
        //nao vai conseguir se inimigo esta morto (supondo inimigo em vetores e nao vamos remover inimigos mortos)
        conseguiu_atacar = realizaAtaque(p, inimigos[inimigo_selecionado - 1], m);

        return conseguiu_atacar;
    }

    //só pq os movimento e ataque tmb tem prepara
    //retorna 1 se procurou, 0 se já foi ou esta em setor privado
    public int preparaProcura(Player p, Mapa m){
        return realizaProcura(p, m);
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
        if (mudou_pos == 0)
            return 0;

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

        if (!ofensivo.getVivo() || !defensivo.getVivo())
            return 0;

        //faz o ataque
        //se for virus 50% de chance de acertar
        //se for player em setor oculto, 50% de chance de acertar
        //se for player em outros setores, acerta
        if (ofensivo instanceof Virus){
            boolean acertou = erraOuAcerta();
            if(acertou){
                defensivo.setDEF(defesa_final);
            }
            return 1;
        }

        else if (ofensivo instanceof Player){
            if(s instanceof Oculto){
                boolean acertou = erraOuAcerta();
                if (acertou)
                    defensivo.setDEF(defesa_final);
            }
            else{
                defensivo.setDEF(defesa_final);
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
        Setor s = m.getSetordoMapa(posX, posY);

        //faz procura retorna -1 caso privado ou já procurado
        int valorDaProcura = s.fazProcura();
        if (valorDaProcura == -1)
            return 0;

        if (valorDaProcura < 4){
            //print que nada foi encontrado
        }
        else if (valorDaProcura == 4){
            //print ganhou 1 de defesa!
            p.setDEF(p.getDEF() + 1);
        }
        else if (valorDaProcura == 5){
            //print ganhou 2 de defesa!
            p.setDEF(p.getDEF() + 2);
        }
        else if (valorDaProcura == 6){
            //print inimigos do setor peredem 1 de defesa!
            Personagem[] inimigos = s.getInimigos();
            int num_inimigos = s.getNumInimigos();
            int i;

            for (i = 0; i < num_inimigos; i++){
                inimigos[i].setDEF(inimigos[i].getDEF() - 1);
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
}

//o que fazer 1:
//mudar definePosicao do player (vai funcionar com base em porta e retornar 0 ou 1) ||feito
//setDefesa ||feito
//mudar realiza ataque pra ver se é setor oculto (player tem 50% de chance de acertar) ||feito
//mudar realiza ataque pra aceitar virus, dai faz o 50% igual ||feito
//mudar realizaProcura pra fazer a procura direto ||feito
//fazer função pra realizar procura e remover do setor ||nao precisa mais
//arrumar realiza movimento ||feito
//faz loop da ação
//INSTANCEOF FAZ A CHECAGEM DE SUPERCLASSE PARA SUBLCASSE (VE SE O PERSONAGEM É VIRUS OU PLAYER) ||boa!

//o que fazer 2:
//loop ação
//impreções nas áreas que pedem impreção
//leak da entrada. da pra fazer so uma e ficar mandando ela para funções diferentes
//ver questão do retorno na procura (seria interessante separar caso já procurado e caso privado, talvez -1 e 0)
//ver se os inimigos vão ser decididos pelo método inicializaPersonagem em vírus ou pelo método inicializaSetor em setor
//ALTEREI O setDef() para não reduzir a negativos e matar caso chegar a zero, dai não precisa fazer isso no ataque

//loop define tipo setor

/*
for (i = 0; i < 5; i++)
    for(j = 0; j < 5; i++)
        m.setSetorDoMapa(i, j) = defineTipoSetor(num aleatório entre 1 e 3(tipo do setor), pos[i, j](manda vetor pra setar posição do setor),
                        matriz3d[i][j] (manda vetor pra setar o vetor das portas), num aleatório entre 1 e 3 (num inimigos));
*/