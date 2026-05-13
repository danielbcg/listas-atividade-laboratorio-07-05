public class Media<E> {
    
    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    //construtor
    public Media(){

        //criaçao da celula sentinela
        Celula<E> sentinela = new Celula<>();


        this.primeiro=this.ultimo=sentinela;
        
        this.tamanho=0;



    }

    

    public double media(int x){


        Celula<E> atual = new Celula<>();
        atual = this.primeiro.getProximo();




        double soma=0;

        double numerosNotNull=0;

        for(int i=0; i<x; i++){



            if(atual!=null){
            //conversao de celula pra double
            soma += (double) atual.getItem();
            atual = atual.getProximo();
            numerosNotNull++;

            }

            

        }

        double media=0;


        media = soma / numerosNotNull;


        return media;

    }






}
