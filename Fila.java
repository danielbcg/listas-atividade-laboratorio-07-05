public class Fila<E> {



    private Celula<E> frente, tras;


    public Fila(){
        
        Celula<E> sentinela = new Celula<>();
        frente=tras=sentinela;
    }

    public Fila(E[] itens){
        Celula<E> sentinela = new Celula<>();
        frente=tras=sentinela;


    if(itens!=null){    //opcional        

        for(int i=0;i<itens.length;i++){
            if(itens[i]!=null){
                enfileirar(itens[i]);
            }
        }
    }

    }



    public Fila(Pilha<E> itens){

        Celula<E> sentinela = new Celula<>();
        frente=tras=sentinela;

        while(!itens.vazia()){

            E item = itens.desempilhar();
            enfileirar(item);

        }


    }



    public int obterNumeroItens(Celula<E> aux){

        if(aux==null){
            return 0;
        }

        if(aux.getItem()!=null){
            return 1 + obterNumeroItens(aux.getProximo());
        }
        else{
            return obterNumeroItens(aux.getProximo());
        }


    }




    public boolean metodoAuxiliarContem(E item){
        return contem(item, frente.getProximo());
    }

    public boolean contem(E item, Celula<E> aux){

        if(aux == null){
            return false;
        }

        if(!aux.getItem().equals(item)){
            return contem(item, aux.getProximo());
        }else{
            return true;
        }
    }











    public Fila<E> unir(Fila<E> fila){

        Fila<E> novaFila = new Fila<>();

     
        Celula<E> atual = frente.getProximo();

        while(atual!=null){

            novaFila.enfileirar(atual.getItem());
            atual=atual.getProximo();

        }

        Celula<E> atual2 = fila.frente.getProximo();

        while(atual2!=null){
            novaFila.enfileirar(atual.getItem());
            atual2=atual2.getProximo();
        }


        return novaFila;
    }





    public boolean vazia(){

        return frente==tras;

    }

    public void enfileirar(E item){

        Celula<E> novaCelula = new Celula<>(item);

        tras.setProximo(novaCelula);
        tras = tras.getProximo();

    }

    public E desenfileirar(){

        if(frente==tras){
            throw new IllegalArgumentException("ERRO.");
        }

        Celula<E> celulaRemovida = frente.getProximo();

        frente.setProximo(celulaRemovida.getProximo());

        if(celulaRemovida==tras){
            tras=frente;
        }

        celulaRemovida.setProximo(null);

        return celulaRemovida.getItem();



    }
    
}
