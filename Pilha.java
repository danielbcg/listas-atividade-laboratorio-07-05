public class Pilha<E> {
    
    private Celula<E> topo;
    private Celula<E> fundo;



    public Pilha(){

        fundo = new Celula<>(null);
        topo = fundo;



    }




    public void empilhar(E item){

        Celula<E> nova = new Celula<>(item);

        nova.setProximo(topo); //a nova celula ta apontando pro antigo topo

        topo=nova; //topo vira nova, mas nova ainda ta apontando pro antigo topo
    }

    public E desempilhar(){

        if(vazia()==true){
            throw new IllegalArgumentException("Nada para desempilhar.");
        }

        E item = topo.getItem();
        topo = topo.getProximo(); //lembra q nova (agr topo) tava apontando pro antigo topo?
                                  //agr ele faz getProximo e vai pra direçao q ele tava apontando

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
