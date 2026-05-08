public class Lista<E> {

    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    public Lista(){
        
        Celula<E> sentinela = new Celula<E>();

        this.primeiro=this.ultimo=sentinela;
        this.tamanho=0;

    }

    public boolean vazia(){
        return (this.primeiro==this.ultimo);
    }

    public void inserir(E novo, int  posicao){
        
        Celula<E> anterior, novaCelula, proximaCelula;

        if((posicao<0)||(posicao>this.tamanho)){
            throw new IndexOutOfBoundsException("Não foi possivel inserir.");
        }

        anterior=this.primeiro;
        for(int i=0;i<posicao;i++){
            anterior=anterior.getProximo();
        }
        novaCelula = new Celula<>(novo);
        proximaCelula = anterior.getProximo();
        anterior.setProximo(novaCelula);
        novaCelula.setProximo(proximaCelula);
        if(posicao==this.tamanho){
            this.ultimo=novaCelula;
        }
        this.tamanho++;
    }


    public E remover(int posicao){


        // 1. Validação de posição inválida ou lista vazia
        if ((posicao < 0) || (posicao >= this.tamanho)) {
            throw new IndexOutOfBoundsException("Não foi possivel remover. Posição inválida.");
        }



        // 2. Declaração das variáveis
        Celula<E> anterior = this.primeiro;
        Celula<E> celulaRemovida;
        Celula<E> proximaCelula;



        // 3. Caminhar até a célula anterior à que será removida
        for(int i=0; i<posicao;i++){
            anterior = anterior.getProximo();
        }
        


        // 4. Isolar a célula a ser removida e reorganizar os ponteiros 
        celulaRemovida = anterior.getProximo();
        proximaCelula = celulaRemovida.getProximo(); 

        //'proximaCelula' agora vai ser a proxima celula (apos celula 'anterior')
        anterior.setProximo(proximaCelula);
        celulaRemovida.setProximo(null);  // Desvincula a célula removida do resto da lista


        // 5. Atualizar o ponteiro 'ultimo' se estivermos removendo o último elemento
        if(celulaRemovida==this.ultimo){
            this.ultimo=anterior;       //???? nao entendi
        }

        this.tamanho--;
        return (celulaRemovida.getItem());

    }






}
