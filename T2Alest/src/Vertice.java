import java.util.ArrayList;

public class Vertice<TIPO> {
    private String valor;
    private TIPO dado;
    private ArrayList<Aresta<TIPO>> arestas;
    
    public Vertice(TIPO dado,String valor){
        this.valor = valor;
        this.dado = dado;
        this.arestas = new ArrayList<Aresta<TIPO>>();
    }

    public TIPO getDado() {
        return dado;
    }

    public void setDado(TIPO dado) {
        this.dado = dado;
    }

    public String getValor() {
        return valor;
    }
    
    public void adicionarAresta(Aresta<TIPO> aresta){
        this.arestas.add(aresta);
    }

    public ArrayList<Aresta<TIPO>> getArestas() {
        return arestas;
    }

    public String toString(){
        return "Dado: "+ dado;
    }
}