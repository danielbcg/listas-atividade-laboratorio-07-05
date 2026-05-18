public class PilhaSemNoCabeça<E> {

    private Celula<E> topo,fundo;

    public PilhaSemNoCabeça(int tamanho){

        fundo = new Celula<>();
        topo=fundo;
        tamanho=0;

    }

    public boolean vazia(){
        return fundo==topo;
    }

    public void empilhar(E item){

        Celula<E> novaCelula = new Celula<>(item);

        novaCelula.setProximo(topo);
        topo=novaCelula;


    }

    public E desempilhar(){

        if(vazia()){
            throw new IllegalArgumentException("nada p desempilhar");
        }

        topo=topo.getProximo();

        return topo.getItem();

    }



}
