import java.util.function.Predicate;

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



    //questao 4
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




    //questao 5
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
            novaFila.enfileirar(atual2.getItem());
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

        Celula<E> celulaDesenfileirada = this.frente.getProximo();

        

        frente.setProximo(celulaDesenfileirada.getProximo());

        if(celulaDesenfileirada.getProximo()==null){
            tras=frente;
        }

        celulaDesenfileirada.setProximo(null);


        
        return celulaDesenfileirada.getItem();

    }


    public Fila<E> filaInvertida(Fila<E> fila){


        Pilha<E> pilhaAuxiliar = new Pilha<>();

        while(!fila.vazia()){
            pilhaAuxiliar.empilhar(fila.desenfileirar());
        }

        while(!pilhaAuxiliar.vazia()){
            fila.enfileirar(pilhaAuxiliar.desempilhar());
        }

        return fila;




    }

    public Fila<E> filaIgualMasInvertida(){

        Fila<E> filaInvertida = new Fila<>();
        Pilha<E> pilhaAuxiliar = new Pilha<>();
        
        //NAO PODE DESTRUIR NEM MODIFICAR FILA
        //PARA ISSO DEVE-SE COPIAR AS CELULAS DA FILA EM OUTRA

        Celula<E> atual = this.frente.getProximo();

        while( atual != null ){
            pilhaAuxiliar.empilhar(atual.getItem()); //copia item da fila pra pilha
            atual = atual.getProximo(); //passa pro proximo
        }

        while(!pilhaAuxiliar.vazia()){

            filaInvertida.enfileirar(pilhaAuxiliar.desempilhar());

        }



        return filaInvertida;

    }



    public void mesclar (Fila<E> fila){

        Fila<E> filaMesclada = new Fila<>();




        while (!vazia() || !fila.vazia()) {

            filaMesclada.enfileirar(desenfileirar());
            filaMesclada.enfileirar(fila.desenfileirar());
            
            
        }

        this.frente = filaMesclada.frente;
        this.tras = filaMesclada.tras;

    }

    public void inverter(){

        Pilha<E> pilhaAuxiliar = new Pilha<>();

        while(!this.vazia()){
            pilhaAuxiliar.empilhar(this.desenfileirar());
        }

        while(!pilhaAuxiliar.vazia()){
            this.enfileirar(pilhaAuxiliar.desempilhar());
        }

    }



    public int quantosAFrente(Celula<E> atual, E item){


        if(atual==null){
            throw new IndexOutOfBoundsException("Item nao encontrado");
        }

        if(!atual.getItem().equals(item) && !atual.getItem().equals(null)){
    
            return  1 + quantosAFrente(atual.getProximo(), item);
        
        }else{
            return 0;
        }



    }

    public Fila<E> dividir(){ //retornar fila de posiçoes pares da original, manter as posiçoes impares

        Fila<E> filaPar = new Fila<>();
        Fila<E> filaImpar = new Fila<>();

        int i=0;


        while(!vazia()){

            E item = desenfileirar();


            if(i%2==0){
                filaPar.enfileirar(item);
            }
            else{
                filaImpar.enfileirar(item);
            }



            i++;


        }



        this.frente=filaImpar.frente;
        this.tras=filaImpar.tras;


        return filaPar;

    }




    //questao 2 atividade avaliativa
    private E localizar(Predicate<E> condicional, Celula<E> atual){

        if(atual==null){
            return null;
        }
        else if(condicional.test(atual.getItem())){
            return atual.getItem();
        }
        else{
            return localizar(condicional, atual.getProximo());
        }

    }

    public E chamaLocalizar(Predicate<E> condicional){
        return localizar(condicional, frente.getProximo());
    }


    //questao gemini

    public Fila<E> clonarFilaRaiz(){

        Fila<E> copia = new Fila<>();

        Celula<E> aux = this.frente.getProximo();

        Celula<E> anterior=copia.frente;

        while(aux!=null){

            Celula<E> nova = new Celula<>(aux.getItem());

            anterior.setProximo(nova);
            anterior=anterior.getProximo();

            aux=aux.getProximo();

        }

        copia.tras=anterior;

        return copia;


    }


    public void mesclarRaiz(Fila<E> fila){

        Fila<E> novaFila = new Fila<>();


        while(!this.vazia() || !fila.vazia()){

            if(!this.vazia()){
                novaFila.enfileirar(desenfileirar());
            }
            
            if(!this.vazia()){
                novaFila.enfileirar(fila.desenfileirar());
            }
            
        }

        this.frente=novaFila.frente;
        this.tras=novaFila.tras;

    }


    public Fila<E> dividirRaiz(){

        Fila<E> filaPar = new Fila<>(); 
        Fila<E> filaImpar = new Fila<>(); 


        int i=0;

        while(!vazia()){

            if(i%2==0){ //elementos pares

                filaPar.enfileirar(desenfileirar());

            }

            else{ //impares

                filaImpar.enfileirar(desenfileirar());

            }

            i++;

        }

        this.frente=filaImpar.frente;
        this.tras=filaImpar.tras;


        return filaPar;



    }

    public void inverterRaiz(){

        Celula<E> anterior =null;
        Celula<E> atual = this.frente.getProximo();
        Celula<E> proximo = null;

        this.tras = atual;

        while(atual!=null){
            proximo = atual.getProximo();
            atual.setProximo(anterior);

            anterior=atual;
            atual=proximo;
        }

        this.frente.setProximo(anterior);


    }

    
    




    




    
}
