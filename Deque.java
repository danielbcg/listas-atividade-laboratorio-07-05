public class Deque<E> {

    private Celula<E> primeiro; //aponta para a sentinela
    private Celula<E> ultimo;   //aponta para o ultimo elemento


    public Deque(){
        primeiro = new Celula<>(null); //célula vazia -> sentinela
        ultimo=primeiro;

    }

    public void inserirInicio(E novo){
        Celula<E> novaCelula = new Celula<>(novo); //criaçao da nova celula

        novaCelula.setProximo(primeiro.getProximo()); //inserindo no inicio
        primeiro.setProximo(novaCelula);

        if(primeiro==ultimo){ //deque estava vazio antes de inserir valor no inicio
            ultimo=novaCelula;
        }


    }

    public void inserirFim(E novo){
        Celula<E> novaCelula = new Celula<>(novo); //criaçao da nova celula

        novaCelula.setProximo(null);
        ultimo.setProximo(novaCelula);

        ultimo=ultimo.getProximo();


    }


    public E removerInicio(){


        Celula<E> celulaRemovida = primeiro.getProximo();

        primeiro.setProximo(celulaRemovida.getProximo());

        if (celulaRemovida == ultimo) {
            ultimo = primeiro;
        }

        celulaRemovida.setProximo(null);

        return celulaRemovida.getItem(); //tem q ter o .getItem() pra poder retornar o item removido
    }



    public E removerFim(){

        if(primeiro==ultimo){
            throw new IllegalArgumentException("Deque vazio");
        }

        Celula<E> i;
        Celula<E> celulaRemovida;


        //i vai andar até a penultima celula
        for(i=primeiro; i.getProximo() != ultimo; i = i.getProximo());

        celulaRemovida = i.getProximo();

        ultimo = i;

        ultimo.setProximo(null);

        return celulaRemovida.getItem();

    }



}
