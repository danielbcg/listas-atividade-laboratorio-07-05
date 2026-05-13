import java.util.function.Predicate;

public class Lista<E> {

    private Celula<E> primeiro;
    private Celula<E> ultimo;
    private int tamanho;

    public Lista(){
        
        Celula<E> sentinela = new Celula<E>();

        this.primeiro=this.ultimo=sentinela;
        this.tamanho=0;

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


        if(x<=0){
            throw new IllegalArgumentException("O valor de x deve ser maior que zero.");
        }
        if(x>this.tamanho){ 
            throw new IllegalArgumentException("O valor de x deve ser menor ou igual ao tamanho da lista.");
        }


        Celula<E> atual = this.primeiro.getProximo();
        double soma = 0.0;

        for(int i=0; i<x ; i++){

            //verifica se o item é um numero antes da soma
            if(atual.getItem() instanceof Number){

                //converte o item generico e extrai como double
                Number valor = (Number) atual.getItem();
                soma+=valor.doubleValue();

            }else{
                throw new ClassCastException("A lista tme elementos q nao sao numeros");
            }

            //avança pro proximo elemento
            atual = atual.getProximo();



        }


        return soma/x;



    }




    public Lista<E> filtrar(Predicate<E> condicao, int x){
        

        if(x<=0){
            throw new IllegalArgumentException("Deve ser maior ou igual a 0");
        }

        //cria lista
        Lista<E> listaFiltrada = new Lista<>();
        
        int elementosEncontrados=0;

        for(Celula<E> atual = this.primeiro.getProximo(); //inicia a busca apos a celula sentinela
            atual!=null && elementosEncontrados<x;
            atual = atual.getProximo()){


                E item = atual.getItem();

                //VERIFICA CONDIÇAO
                if(condicao.test(item)){
                    listaFiltrada.inserir(item, listaFiltrada.tamanho);
                    elementosEncontrados++;
                }


                


        }


        return listaFiltrada;


    }






}
