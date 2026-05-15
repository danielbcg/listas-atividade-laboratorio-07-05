public class Celula<T> {

    private final T item;
    private Celula<T> proximo;
    private Celula<T> anterior; // <-- O novo atributo para olhar para trás

    // Construtor para Sentinela (vazia)
    public Celula() {
        this.item = null;
        this.proximo = null;
        this.anterior = null;
    }

    // Construtor para novo elemento
    public Celula(T item) {
        this.item = item;
        this.proximo = null;
        this.anterior = null;
    }

    // Construtor completo (opcional, mas ajuda muito)
    public Celula(T item, Celula<T> proximo, Celula<T> anterior) {
        this.item = item;
        this.proximo = proximo;
        this.anterior = anterior;
    }
    
    public T getItem() {
        return item;
    }

    public Celula<T> getProximo() {
        return proximo;
    }

    public void setProximo(Celula<T> proximo) {
        this.proximo = proximo;
    }

    // Novos Métodos para o ponteiro anterior
    public Celula<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(Celula<T> anterior) {
        this.anterior = anterior;
    }
}