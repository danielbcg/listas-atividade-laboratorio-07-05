

public class Lista<E extends Comparable<E>> {

    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    public Lista(){
        
        Celula<E> sentinela = new Celula<E>();

        this.primeiro=this.ultimo=sentinela;
        this.tamanho=0;

    }

    public Lista(Fila<E> f1, Fila<E> f2) {
    this.primeiro = new Celula<>();
    this.ultimo = this.primeiro;

    E i1 = null;
    E i2 = null;

    while ((!f1.vazia() || i1 != null) && (!f2.vazia() || i2 != null)) {
        if (i1 == null) i1 = f1.desenfileirar();
        if (i2 == null) i2 = f2.desenfileirar();

        if (i1.compareTo(i2) <= 0) {
            inserirFinal(i1);
            i1 = null; 
        } else {
            inserirFinal(i2);
            i2 = null; 
        }
    }

    if (i1 != null) inserirFinal(i1);
    while (!f1.vazia()) {
        inserirFinal(f1.desenfileirar());
    }

    if (i2 != null) inserirFinal(i2);
    while (!f2.vazia()) {
        inserirFinal(f2.desenfileirar());
    }
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




    //questao 2 listas flexiveis estudo dirigido
    public void unir(Pilha<E> pilha){

        Pilha<E> pilhaAuxiliar = new Pilha<E>(); //criaçao da pilha

        while(!pilha.vazia()){
            pilhaAuxiliar.empilhar(pilha.desempilhar()); //inverte
        }

        while(!pilhaAuxiliar.vazia()){
            this.inserirFinal(pilhaAuxiliar.desempilhar()); //começa empilhando na posiçao final, mas lista vazia
        }

    }

    //questao 3
    public int contarQtos(E item, Celula<E> aux){

        if(aux==null){
            return 0;
        }

        if(aux.getItem().equals(item)){
            return 1 + contarQtos(item, aux.getProximo());
        }
        else{
            return contarQtos(item, aux.getProximo());
        }

        
    }

    //questao 4
    public boolean contem(E item, Celula<E> aux){

        if(aux==null){
            return false;
        }

        if(aux.getItem().equals(item)){
            return true;
        }
        else{
            return contem(item, aux.getProximo());
        }


    }

    public  boolean contemAuxiliar(E item){

        return contem(item, primeiro.getProximo());

    
    
    }

    


    //questao 5

    public void mesclar(Lista<E> lista){


        Lista<E> listaAuxiliar = new Lista<>();

        Celula<E> atualThis = this.primeiro.getProximo();
        Celula<E> atualParametro = lista.primeiro.getProximo();

        while(atualThis!=null || atualParametro!=null){
            
            if(!atualThis.getItem().equals(null)){
                listaAuxiliar.inserirFinal(atualThis.getItem());
                atualThis=atualThis.getProximo();
            }
            
            if(!atualParametro.getItem().equals(null)){
                listaAuxiliar.inserirFinal(atualParametro.getItem());
                atualParametro=atualParametro.getProximo();
            }

        }

        this.primeiro=listaAuxiliar.primeiro;
        this.ultimo=listaAuxiliar.ultimo;
        this.tamanho=listaAuxiliar.tamanho;




    }


    //questao 6
    public Lista<E> listaInvertida(){

        Lista<E> novaLista = new Lista<>();

        Celula<E> aux = this.primeiro.getProximo();

        Pilha<E> pilhaAuxiliar = new Pilha<>();

        while(aux!=null){

            pilhaAuxiliar.empilhar(aux.getItem());
            aux=aux.getProximo();

        }

        while (!pilhaAuxiliar.vazia()) {

            novaLista.inserirFinal(pilhaAuxiliar.desempilhar());
            novaLista.tamanho++;
            
        }


        return novaLista;
    }


    //questao 7

    public void inverter(){

        Lista<E> listaAuxiliar = new Lista<>();

        Pilha<E> pilhaAuxiliar = new Pilha<>();

        Celula<E> atual = this.primeiro.getProximo();

        while(atual!=null){

            pilhaAuxiliar.empilhar(atual.getItem());
            atual = atual.getProximo();

        }

        while(!pilhaAuxiliar.vazia()){

            listaAuxiliar.inserirFinal(pilhaAuxiliar.desempilhar());
            listaAuxiliar.tamanho++;

        }

        this.primeiro=listaAuxiliar.primeiro;
        this.ultimo=listaAuxiliar.ultimo;
        this.tamanho=listaAuxiliar.tamanho;



    }

    //questao 8
    public Lista<E> inverteConcatenaEMescla(Lista<E> listaOutra){


        Lista<E> listaAuxiliar1 = new Lista<>();
        Lista<E> listaAuxiliar2 = new Lista<>();

        Lista<E> novaLista = new Lista<>();

        Pilha<E> pilhaAuxiliar1 = new Pilha<>();
        Pilha<E> pilhaAuxiliar2 = new Pilha<>();

        Celula<E> auxThis = this.primeiro.getProximo();
        Celula<E> auxParam = listaOutra.primeiro.getProximo();


        //inverte a this sem destruir ela
        while(auxThis!=null){

            pilhaAuxiliar1.empilhar(auxThis.getItem());
            auxThis=auxThis.getProximo();

        }

        while(!pilhaAuxiliar1.vazia()){
            listaAuxiliar1.inserirFinal(pilhaAuxiliar1.desempilhar());
            listaAuxiliar1.tamanho++;
        }


        //inverte a lista do parametro sem destruir ela
        while(auxParam!=null){
            pilhaAuxiliar2.empilhar(auxParam.getItem());
            auxParam=auxParam.getProximo();
        }

        while(!pilhaAuxiliar2.vazia()){
            listaAuxiliar2.inserirFinal(pilhaAuxiliar2.desempilhar());
            listaAuxiliar2.tamanho++;   
        }

        //mescla as listas auxiliares

        Celula<E> atualLista1 = listaAuxiliar1.primeiro.getProximo();
        Celula<E> atualLista2 = listaAuxiliar2.primeiro.getProximo();

        while(atualLista1!=null || atualLista2!=null){

            if( atualLista1!=null ){
                novaLista.inserirFinal(atualLista1.getItem());
                atualLista1 = atualLista1.getProximo();
            }

            if( atualLista2!=null ){
                novaLista.inserirFinal(atualLista2.getItem());
                atualLista2 = atualLista2.getProximo();
            }

        }

        return novaLista;

        





    }

    //questao 9
    public Lista<E> clone(){

        Lista<E> listaCopia = new Lista<>();

        Celula<E> aux = this.primeiro.getProximo();

        while(aux!=null){
            listaCopia.inserirFinal(aux.getItem());
            listaCopia.tamanho++;
            aux = aux.getProximo();
        }

        return listaCopia;

    }





    





}
