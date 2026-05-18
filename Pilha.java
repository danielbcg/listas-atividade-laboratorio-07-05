public class Pilha<E> {
    
    private Celula<E> topo;
    private Celula<E> fundo;



    public Pilha(){
        fundo = new Celula<>();
        topo=fundo;
    }

    public E empilhar(E item){

        Celula<E> nova = new Celula<>(item); //cria a nova celula

        nova.setProximo(topo); //nova celula vai apontar pro antigo topo

        topo=nova; //topo vira nova, e nova ainda ta apontando pro antigo topo

        return nova.getItem();

    }

    public E desempilhar(){

        if(topo==fundo){
            throw new IllegalArgumentException("Nada para desempilhar.");
        }

        topo = topo.getProximo(); //lembra q nova (q agora é topo) tava apontando pro antigo topo?

        return topo.getItem();




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



    public void concatenar(Pilha<E> pilha){


        Pilha<E> pilhaAuxiliar = new Pilha<>();


        while(!pilha.vazia()){


            E itemDesempilhado = pilha.desempilhar();

            pilhaAuxiliar.empilhar(itemDesempilhado);
        }

        while(!pilhaAuxiliar.vazia()){


            E itemDesempilhado = pilhaAuxiliar.desempilhar();

            this.empilhar(itemDesempilhado);

        }



        


    }


    public int obterNumeroItens(){

        int itens=0;

        Celula<E> i = topo;

        

        while(i!=fundo){

            itens++;

            i=i.getProximo();

        }




        return itens;
    }

    public Pilha<E> inverter(){

        Pilha<E> pilhaAux1 = new Pilha<>();
        Pilha<E> pilhaAux2 = new Pilha<>();
        
        while(!vazia()){
            E item = desempilhar();
            pilhaAux1.empilhar(item);
        }

        while(!pilhaAux1.vazia()){
            E item2 = pilhaAux1.desempilhar();
            pilhaAux2.empilhar(item2);
        }

        while(!pilhaAux2.vazia()){
            E item3 = pilhaAux2.desempilhar();
            empilhar(item3);
        }

        
        return this;
        

    }



    public Pilha<E> excluirElementosAntigos(double porcentagem){

        double calculo = obterNumeroItens()*porcentagem;
        double sobraram = obterNumeroItens()*(1-porcentagem);
        

        if(obterNumeroItens()<=0 || vazia()){
            throw new IllegalArgumentException("ERRO.");
        }

        if( calculo % 1 != 0 ){
            calculo=calculo-calculo%1; //arredonda pra baixo
        }
        
        if( sobraram % 1 != 0 ){
            sobraram=sobraram-sobraram%1; //arredonda pra baixo
        }

        //desempilhar até chegar na quantidade que devo retirar

        Pilha<E> pilhaAuxiliar = new Pilha<>();
        Pilha<E> excluidos = new Pilha<>();
        


        while(sobraram>0){
            E item = desempilhar();
            pilhaAuxiliar.empilhar(item);

            sobraram--;
        }

        while(calculo>0){
            E item2 = desempilhar();
            excluidos.empilhar(item2);

            calculo--;
        }

        //reempilhar na original
        while(!pilhaAuxiliar.vazia()){
            E item3 = pilhaAuxiliar.desempilhar();
            empilhar(item3);
        }


        return this;








    }


    public void mesclar(Pilha<E> pilhaParam){

        Pilha<E> novaPilha = new Pilha<>();

        Pilha<E> pilhaThisAux = new Pilha<>();
        Pilha<E> pilhaParamAux = new Pilha<>();

        while (!vazia()) {

            pilhaThisAux.empilhar(desempilhar());
            
        }

        while (!pilhaParam.vazia()) {

            pilhaParamAux.empilhar(pilhaParam.desempilhar());
            
        }


        while (!pilhaThisAux.vazia() || !pilhaParamAux.vazia()) {

            if(!pilhaThisAux.vazia()){
                novaPilha.empilhar(pilhaThisAux.desempilhar());
            }
            if(!pilhaParamAux.vazia()){
                novaPilha.empilhar(pilhaParamAux.desempilhar());
            }
            
        }

        this.topo=novaPilha.topo;
        this.fundo=novaPilha.fundo;


    }



    //copia sem destruir a pilha atual
    public Pilha<E> copiando(){

        Pilha<E> pilhaAux = new Pilha<E>();
        Pilha<E> cópia = new Pilha<E>();

        Celula<E> auxThis = this.topo;
        Celula<E> auxPilha = pilhaAux.topo;


        while(auxThis!=null && auxThis!=this.fundo){
            pilhaAux.empilhar(auxThis.getItem());
            auxThis=auxThis.getProximo();
        }

        while(auxPilha!=null && auxPilha!=this.fundo){
            cópia.empilhar(auxPilha.getItem());
            auxPilha = auxPilha.getProximo();
        }

        return cópia;

    }


    public Pilha<E> copiaRaiz(){

        Pilha<E> copia = new Pilha<>();

        Celula<E> aux = this.topo;

        Celula<E> nova = new Celula<>(aux.getItem());
        nova.setProximo(copia.fundo);
        copia.topo=nova;
        Celula<E> anterior = nova;

        while(aux.getProximo()!=this.fundo){

            aux=aux.getProximo();

            nova = new Celula<>(aux.getItem());
            nova.setProximo(copia.fundo);
            anterior.setProximo(nova);
            anterior=anterior.getProximo();


        }

        return copia;


    }


    


    

}
