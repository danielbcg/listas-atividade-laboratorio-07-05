public class DequeDuplo<E extends Comparable<E>> {
    
    private Celula<E> primeiro, ultimo;
    private int tamanho;

    public DequeDuplo(){
        primeiro = new Celula<>(null); //sentinela
        ultimo=primeiro;
        tamanho=0;
    }

    public E inserirInicio(E item){

        Celula<E> novaCelula = new Celula<>(item);

        novaCelula.setProximo(primeiro.getProximo());
        novaCelula.setAnterior(primeiro);

        if(primeiro.getProximo()!=null){
            primeiro.getProximo().setAnterior(novaCelula);
        }

        primeiro.setProximo(novaCelula);


        if(primeiro==ultimo){
            ultimo=novaCelula;
        }


        return novaCelula.getItem();


    }

    public E inserirFim(E item){

        Celula<E> novaCelula = new Celula<>(item);

        ultimo.setProximo(novaCelula);
        novaCelula.setAnterior(ultimo);
        novaCelula.setProximo(null);
        ultimo=novaCelula;


        return novaCelula.getItem();

    }

    public E removerInicio(){

        if(primeiro==ultimo){
            throw new IllegalArgumentException("Nada para remover.");
        }

        Celula<E> celulaRemovida = primeiro.getProximo();

        primeiro.setProximo(celulaRemovida.getProximo());

        if(celulaRemovida.getProximo()!=null){
            celulaRemovida.getProximo().setAnterior(primeiro);
        }else{
            ultimo=primeiro;
        }

        celulaRemovida.setAnterior(null);
        celulaRemovida.setProximo(null);

        return celulaRemovida.getItem();




    }

    public E removerFim(){

        if(ultimo==primeiro){
            throw new IllegalArgumentException("Nada para remover.");
        }

        Celula<E> celulaRemovida = ultimo;

        ultimo=ultimo.getAnterior();

        ultimo.setProximo(null);

        celulaRemovida.setAnterior(null);
        celulaRemovida.setProximo(null);

        
        return celulaRemovida.getItem();

    }

    public E inserir(E item, int posicao){


        if(posicao<0 || posicao>=tamanho){
            throw new IndexOutOfBoundsException("ERRO");
        }
        if(posicao==0){
            return inserirInicio(item);
        }
        if(posicao==tamanho-1){
            return inserirFim(item);
        }



        Celula<E> anterior = primeiro; 

        for(int i=0; i<posicao; i++){
            anterior=anterior.getProximo();
        }

        Celula<E> novaCelula = new Celula<>(item);

        novaCelula.setProximo(anterior.getProximo());
        anterior.setProximo(novaCelula);

        tamanho++;


        return novaCelula.getItem();





    }




}
