public class Pilha<E> {
    
    private Celula<E> topo;
    private Celula<E> fundo;



    public Pilha(){

        fundo = new Celula<>(null);
        topo = fundo;



    }




    public void empilhar(E item){

        Celula<E> nova = new Celula<>(item);

        nova.setProximo(topo);

        topo=nova;
    }

    public E desempilhar(){

        if(vazia()==true){
            throw new IllegalArgumentException("Nada para desempilhar.");
        }

        E item = topo.getItem();
        topo = topo.getProximo();

        return item;



    }

    public E consultarTopo(){

        return topo.getItem();
    }


    public boolean vazia(){
        boolean vazio=false;

        if(topo==fundo){
            vazio=true;
        }

        return vazio;
    }
}
