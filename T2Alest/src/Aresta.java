public class Aresta<TIPO> {
    private Vertice<TIPO> inicio;
    private Vertice<TIPO> fim;
    
    public Aresta(Vertice<TIPO> inicio, Vertice<TIPO> fim){
        this.inicio = inicio;
        this.fim = fim;
    }

    public Vertice<TIPO> getFim() {
        return fim;
    }
}