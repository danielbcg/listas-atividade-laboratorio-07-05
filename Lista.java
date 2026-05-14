import java.util.function.Predicate;

public class Lista<E extends Comparable<E>> {

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

        Celula<E> novaCelula = new Celula<>(valor);
        this.ultimo.setProximo(novaCelula);
        this.ultimo = novaCelula;
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


    public Lista<E> listaFiltrada(double condicao, double quantidade){




        Lista<E> novaLista = new Lista<>();

        Celula<E> anterior = this.primeiro.getProximo();





        for(int i=0; i<quantidade && anterior!=null; i++){

            double valorItem = (double) anterior.getItem(); // ver com a prof se ela
                                                            //deixa usar só (double)
                                                            //ou parseDouble

            if(valorItem > condicao){

                novaLista.inserirFinal(anterior.getItem());

            }

            anterior = anterior.getProximo();


        }


        return novaLista;

    }



    public Lista<E> dividir(E item ){
        
        
        Lista<E> novaLista = new Lista<>();

        Celula<E> anterior = primeiro;

        while(anterior.getProximo()!=null){

            E produto = anterior.getProximo().getItem();


            if(produto.compareTo(item)>=0){

                Celula<E> celulaRemovida = anterior.getProximo();

                novaLista.inserirFinal(celulaRemovida.getItem());

                anterior.setProximo(celulaRemovida.getProximo());

                if(celulaRemovida==this.ultimo){
                    this.ultimo = anterior;
                }

                this.tamanho--;



            }else{

                anterior=anterior.getProximo();

            }


        }


        return novaLista;

        
    }



    //questao do gemini
    public Lista<E> separarPorAno(E livroReferencia){

        Lista<E> novaLista = new Lista<>();

        Celula<E> anterior=primeiro; //começa na sentinela
        

        while(anterior.getProximo()!=null){


            E livro = anterior.getProximo().getItem();


            if(livro.compareTo(livroReferencia)>=0){

                Celula<E> livroRemovido = anterior.getProximo();

                novaLista.inserirFinal(livroRemovido.getItem());

                anterior.setProximo(livroRemovido.getProximo());

                if(livroRemovido==this.ultimo){
                    this.ultimo=anterior;
                }
                
                this.tamanho--;




            }else{
                anterior=anterior.getProximo();
            }


        }


        return novaLista;


    }





    





}
