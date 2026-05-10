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


    public void inserirFinal(E valor){

        Celula<E> novo = new Celula<>(valor);
        this.ultimo.setProximo(novo);
        this.ultimo=novo; // n entendi 
        this.tamanho++; //aumenta tamanho da lista pq é flexivel




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






    public double media(int x){


        double soma = 0;

        Celula<E> anterior = this.primeiro.getProximo();

        double elementosNaoVazios=0;


        if(anterior != null){

            for(int i=0; i<x; i++) {

                soma += (double) anterior.getItem();

                anterior = anterior.getProximo();

                elementosNaoVazios++;
            }




        }



        double media=0;


        media = soma / elementosNaoVazios;

        return media;


    }







    public Lista<E> filtrar(double condicao, int quantidade){


        //nova lista
        Lista<E> lista = new Lista<>();

        Celula<E> anterior = this.primeiro.getProximo();

        if(condicao<0 || quantidade<1){ // pelo menos 1 elemento pra quantidade

            for(int i=0; i<quantidade && anterior != null ; i++){

                double valorItem = (double) anterior.getItem();

                if(valorItem>=condicao){

                    lista.inserirFinal(anterior.getItem());

                }

                anterior = anterior.getProximo();

            }

        }


        return lista;
    }






}
